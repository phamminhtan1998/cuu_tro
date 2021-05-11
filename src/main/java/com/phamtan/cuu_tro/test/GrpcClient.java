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

import java.io.*;
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

    public void uploadMultiFile(List<String> paths ) throws IOException, InterruptedException {
        System.out.println("Running gprc upload file ");
        long startTime = System.currentTimeMillis();
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
        for (String urlfile : paths){
            uploadFile(stub,urlfile);
        }
        channel.shutdown();

    }
    public void uploadFile(FileServiceGrpc.FileServiceStub stub,String urlFile) throws IOException, InterruptedException{
        String[] listString = urlFile.split(Pattern.quote("/"));
        String[] nameAndTypeFile = listString[listString.length-1].split(Pattern.quote("."));
        String nameFile = listString[listString.length-1]
                .replace("."+nameAndTypeFile[nameAndTypeFile.length-1],"");
        StreamObserver<FileOuterClass.FileUploadRequest> streamObserver = stub.upload(new FileUploadObserver());
        Path path = Paths.get(urlFile);
        final ClientCallStreamObserver<FileOuterClass.FileUploadRequest> clientCallStreamObserver = (ClientCallStreamObserver<FileOuterClass.FileUploadRequest>) streamObserver;

        FileOuterClass.FileUploadRequest metadata = FileOuterClass.FileUploadRequest.newBuilder()
                .setMetadata(FileOuterClass.MetaData.newBuilder()
                        .setName(nameFile)
                        .setType(nameAndTypeFile[nameAndTypeFile.length - 1]).build()).build();

        streamObserver.onNext(metadata);
        int chunkSize = 1024 * 1024 * 10;
        InputStream inputStream = null;
        try  {
            byte[] bytes;
            File fileSource = new File(urlFile);
              inputStream = new BufferedInputStream(new FileInputStream(fileSource));
/**
 *  Create for size of message per request
 *  If size of file less than chunk size , size of message equal size of file
 */

              if(fileSource.length()<chunkSize){
                   bytes = new byte[(int) fileSource.length()];
              }
              else {
                  bytes = new byte[chunkSize];
              }

            while (true) {
                if (inputStream.available() != 0) {
//                    Change this when need more read file
                    if(inputStream.available()<=chunkSize){
                        chunkSize=inputStream.available();
                    }
                    inputStream.mark(chunkSize);
                    inputStream.read(bytes);
                    if (clientCallStreamObserver.isReady()) {
                        FileOuterClass.FileUploadRequest uploadRequest = FileOuterClass.FileUploadRequest.newBuilder()
                                .setFile(FileOuterClass.File.newBuilder()
                                        .setContent(ByteString.copyFrom(bytes, 0,chunkSize))
                                        .build()).build();
                        streamObserver.onNext(uploadRequest);
                    } else {
                        inputStream.reset();
                    }
                } else {
                    inputStream.close();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        streamObserver.onCompleted();

    }

}
