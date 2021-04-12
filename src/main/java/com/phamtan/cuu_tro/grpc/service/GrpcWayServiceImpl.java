package com.phamtan.cuu_tro.grpc.service;


import com.example.grpc_base.model.WayScopedBean;
import com.example.grpc_base.proto.Way;
import com.example.grpc_base.proto.WayServiceGrpc;
import com.phamtan.cuu_tro.grpc.req.InputStreamObserver;
import com.phamtan.cuu_tro.servie.NewWayService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@GrpcService
public class GrpcWayServiceImpl extends WayServiceGrpc.WayServiceImplBase {
    @Autowired
    private WayScopedBean wayScopedBean;
    @Autowired
    private  NewWayService newWayService;
    @Override
    public StreamObserver<Way.WayRequest> createWay( StreamObserver<Way.WayResponse> responseObserver) {
        return new InputStreamObserver(newWayService,wayScopedBean,responseObserver);
    }
}
