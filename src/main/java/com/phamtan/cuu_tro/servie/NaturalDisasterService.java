package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.custom.DisasterMongoQuery;
import com.phamtan.cuu_tro.dao.entity.NaturalDisaster;
import com.phamtan.cuu_tro.dao.repo.DisasterRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NaturalDisasterService {
    private final DisasterRepo disasterRepo;
    private final DisasterMongoQuery disasterMongoQuery;

    public List<NaturalDisaster> getAll(){
        return disasterRepo.findAll();
    }
    public NaturalDisaster getById(String id){
        return disasterRepo.findById(id).get();
    }
    public List<NaturalDisaster> getAllNear(GeoJsonPoint point, double distanceByKM){
        Distance distance = new Distance(distanceByKM, Metrics.MILES);
       return disasterMongoQuery.getWithPoint(point,distanceByKM);
    }
    public List<NaturalDisaster> getAllByDisasterType(DisasterType type){
        return disasterRepo.findAllByDisasterType(type);
    }
    public List<NaturalDisaster> getAllByDisasterTypeAndDistance(GeoJsonPoint point,double distance,DisasterType type){
        Distance distance1 = new Distance(distance, Metrics.KILOMETERS);
        return disasterMongoQuery.getWithTypeAndPoint(point,distance,type);
//        return disasterRepo.findAllByDisasterTypeAndCoordinateNear(type,point,distance1);
    }

    public NaturalDisaster create(NaturalDisaster naturalDisaster){
      return   disasterRepo.save(naturalDisaster);
    }
    public NaturalDisaster update(NaturalDisaster naturalDisaster){
        if(disasterRepo.existsById(naturalDisaster.getId())){
            return   disasterRepo.save(naturalDisaster);
        }
        return null;

    }
    public StatusBasic delete(NaturalDisaster naturalDisaster){
        if(disasterRepo.existsById(naturalDisaster.getId())){
               disasterRepo.delete(naturalDisaster);
               return StatusBasic.SUCCESSFUL;
        }
        return StatusBasic.ERROR;
    }
}
