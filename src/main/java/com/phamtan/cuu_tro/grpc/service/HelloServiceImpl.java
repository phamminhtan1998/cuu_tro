package com.phamtan.cuu_tro.grpc.service;

import com.example.grpc_base.proto.GreeterGrpc;
import com.example.grpc_base.proto.Hello;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(Hello.HelloRequest request, StreamObserver<Hello.HelloReply> responseObserver) {
        responseObserver.onNext(Hello.HelloReply.newBuilder()
                .setMessage("Method Impl from cuu tro  server duplicated")
                .build());

        responseObserver.onCompleted();
    }
}   
