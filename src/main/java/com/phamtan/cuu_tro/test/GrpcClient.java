package com.phamtan.cuu_tro.test;

import com.example.grpc_base.proto.FileOuterClass;
import com.example.grpc_base.proto.FileServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.ClientCallStreamObserver;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import org.apache.catalina.Executor;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class GrpcClient  {

    public void uploadFile(String urlFile ) throws IOException, InterruptedException {
        System.out.println("Running gprc upload file ");
        long startTime = System.currentTimeMillis();
        String[] listString = urlFile.split(Pattern.quote("/"));
        String[] nameAndTypeFile = listString[listString.length-1].split(Pattern.quote("."));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                100,
                2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(500)
        );
        ManagedChannel channel = NettyChannelBuilder.forAddress(
                "localhost",9090
        ).usePlaintext().build();
        FileServiceGrpc.FileServiceStub stub = FileServiceGrpc.newStub(channel);
        stub.withExecutor(threadPoolExecutor);
        StreamObserver<FileOuterClass.FileUploadRequest> streamObserver =stub.upload(new FileUploadObserver(startTime));
        Path path = Paths.get(urlFile);
        final ClientCallStreamObserver<FileOuterClass.FileUploadRequest>  clientCallStreamObserver = (ClientCallStreamObserver<FileOuterClass.FileUploadRequest>) streamObserver;

        FileOuterClass.FileUploadRequest metadata = FileOuterClass.FileUploadRequest.newBuilder()
                .setMetadata(FileOuterClass.MetaData.newBuilder()
                .setName(nameAndTypeFile[nameAndTypeFile.length-2])
                .setType(nameAndTypeFile[nameAndTypeFile.length-1]).build()).build();

        streamObserver.onNext(metadata);
        int chunkSize = 1024 * 1024 * 50;
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(path))) {
            byte[] bytes = new byte[chunkSize];
            while (true) {
                if( inputStream.available()!=0){
                    inputStream.mark(chunkSize);
                    inputStream.read(bytes);
                    if (clientCallStreamObserver.isReady()) {
                        FileOuterClass.FileUploadRequest uploadRequest = FileOuterClass.FileUploadRequest.newBuilder()
                                .setFile(FileOuterClass.File.newBuilder().setContent(ByteString.copyFrom(bytes, 0, chunkSize))
                                        .build()).build();
                        streamObserver.onNext(uploadRequest);
                    } else {
                        inputStream.reset();
                    }
                }else {
                    inputStream.close();
                    break;
                }
            }
        }
        streamObserver.onCompleted();
        channel.shutdownNow();
    }

}
