package com.phamtan.cuu_tro.grpc.req;

import com.example.grpc_base.model.WayScopedBean;
import com.example.grpc_base.proto.Way;
import com.phamtan.cuu_tro.common.enumeration.MessageRepose;
import com.phamtan.cuu_tro.common.enumeration.NewWayType;
import com.phamtan.cuu_tro.dao.entity.NewWay;
import com.phamtan.cuu_tro.servie.NewWayService;
import com.phamtan.cuu_tro.util.GeoJsonConvert;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class InputStreamObserver implements StreamObserver<Way.WayRequest> {

    private final  NewWayService newWayService;
    private final  WayScopedBean scopedBean;
    private final StreamObserver<Way.WayResponse> outputStreamObserver;

    public InputStreamObserver(NewWayService newWayService, WayScopedBean scopedBean, StreamObserver<Way.WayResponse> outputStreamObserver) {
        this.newWayService = newWayService;
        this.scopedBean = scopedBean;
        this.outputStreamObserver = outputStreamObserver;
    }

    List<GeoJsonPoint> list = new ArrayList<>();
    @Override
    public void onNext(Way.WayRequest wayRequest) {
        log.info("Receiving coordinate with value => lat: {},lon: {} ",wayRequest.getLat(),wayRequest.getLon());
        GeoJsonPoint point=  GeoJsonConvert.convertLatLonToGeoPoint(wayRequest.getLon(), wayRequest.getLat());
        list.add(point);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("Create Way Error with message {}",throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println(scopedBean.toString());
        NewWay newWay = new NewWay();
        newWay.setDescription(scopedBean.getDescription());
        newWay.setIdPerson(scopedBean.getIdPerson());
        newWay.setLocation(scopedBean.getLocation());
        newWay.setWayType(scopedBean.getNewWayType().equals("ROAD")? NewWayType.ROAD:NewWayType.WATERWAY);
        newWay.setName(scopedBean.getName());
        newWay.setWayCoordinate(list);
        newWayService.create(newWay);

        Way.WayResponse  wayResponse = Way.WayResponse.newBuilder()
                .setId(newWay.getId())
                .setDescription(newWay.getDescription())
                .setName(newWay.getName())
                .setMessage(MessageRepose.SUCCESSFUL.getMessage())
                .setLocation(newWay.getLocation())
                .build();
        outputStreamObserver.onNext(wayResponse);
        outputStreamObserver.onCompleted();
    }
}
