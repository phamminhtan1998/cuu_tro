package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.common.enumeration.DangerLevel;
import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.RequestRescue;
import com.phamtan.cuu_tro.dao.repo.RequestRescueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestRescueService {
    @Autowired
    private RequestRescueRepo requestRescueRepo;

    public List<RequestRescue> getAll(){
        return requestRescueRepo.findAll();
    }
    public List<RequestRescue> getByLevel(DangerLevel level){
        return requestRescueRepo.findAllByLevelOrderByTimeDesc(level);
    }
    public List<RequestRescue> getByLocation(GeoJsonPoint point,double distance){
        Distance distance1 = new Distance(distance, Metrics.KILOMETERS);
        return requestRescueRepo.findAllByLocationNearOrderByTimeDesc(point,distance1);
    }
    public List<RequestRescue> getByPerson(String personId){
        return requestRescueRepo.findByIdPerson(personId);
    }


    public RequestRescue create(RequestRescue requestRescue){
        return  requestRescueRepo.save(requestRescue);
    }
    public  RequestRescue update(RequestRescue requestRescue){
        if (requestRescueRepo.existsById(requestRescue.getId())){
            return  requestRescueRepo.save(requestRescue);
        }
            return null;
    }
    public StatusBasic delete(RequestRescue requestRescue){
        if (requestRescueRepo.existsById(requestRescue.getId())){
              requestRescueRepo.delete(requestRescue);
              return StatusBasic.SUCCESSFUL;
        }
        return StatusBasic.ERROR;
    }
}
