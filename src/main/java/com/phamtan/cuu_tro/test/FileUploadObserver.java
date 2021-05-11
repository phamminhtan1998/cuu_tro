package com.phamtan.cuu_tro.test;

import com.example.grpc_base.proto.FileOuterClass;
import io.grpc.stub.StreamObserver;

import java.time.Duration;

public class FileUploadObserver implements StreamObserver<FileOuterClass.FileUploadResponse> {
    long startTime ;

    public FileUploadObserver(long startTime) {
        this.startTime = startTime;
    }

    FileOuterClass.FileUploadResponse fileUploadResponse;
    @Override
    public void onNext(FileOuterClass.FileUploadResponse value) {
        fileUploadResponse = value;
        System.out.println(
                "File upload status :: " + value.getStatus()
        );
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(t.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Received message from server : " +fileUploadResponse.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Time processing :  "+ Duration.ofMillis(endTime-startTime).getSeconds());
    }
}
