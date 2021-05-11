package com.phamtan.cuu_tro.grpc.service;

import com.example.grpc_base.proto.FileOuterClass;
import com.example.grpc_base.proto.FileServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

@GrpcService
public class GrpcUploadFileImpl  extends FileServiceGrpc.FileServiceImplBase {
    private static  final Path SERVER_BASE_PATH = Paths.get("/media/allinone/Learn");
    @Override
    public StreamObserver<FileOuterClass.FileUploadRequest> upload(StreamObserver<FileOuterClass.FileUploadResponse> responseObserver) {
       return new StreamObserver<FileOuterClass.FileUploadRequest>() {
           OutputStream writer;
           FileOuterClass.Status status = FileOuterClass.Status.IN_PROGRESS;

           @Override
           public void onNext(FileOuterClass.FileUploadRequest  fileUploadRequest) {
                try {
                    System.out.println("File upload request !");
                    if(fileUploadRequest.hasMetadata()){
                        writer = getFilePath(fileUploadRequest);
                    }else{
                        writeFile(writer, fileUploadRequest.getFile().getContent());
                    }
                }catch (Exception ex){
                    this.onError(ex);
                }
           }

           @Override
           public void onError(Throwable t) {
                status= FileOuterClass.Status.FAILED;
                this.onCompleted();
           }

           @Override
           public void onCompleted() {
                closeFile(writer);
               status = FileOuterClass.Status.IN_PROGRESS.equals(status) ? FileOuterClass.Status.SUCCESS : status;
               FileOuterClass.FileUploadResponse response = FileOuterClass.FileUploadResponse.newBuilder()
                       .setStatus(status)
                       .build();
               responseObserver.onNext(response);

               responseObserver.onCompleted();
           }
       };
    }
    private OutputStream getFilePath(FileOuterClass.FileUploadRequest request) throws IOException {
        var fileName = request.getMetadata().getName() + "." + request.getMetadata().getType();
       OutputStream outputStream =  Files.newOutputStream(SERVER_BASE_PATH.resolve(fileName), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return  outputStream;
    }

    private void writeFile(OutputStream writer, ByteString content) throws IOException {
        writer.write(content.toByteArray());
        writer.flush();
    }

    private void closeFile(OutputStream writer){
        try {
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
