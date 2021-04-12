package com.phamtan.cuu_tro.dao.custom;

import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.dao.entity.NaturalDisaster;
import com.phamtan.cuu_tro.dao.entity.RequestRescue;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomMongoQuery {
    private final MongoTemplate mongoTemplate;

    public List<NaturalDisaster> getDisasterWithPoint(GeoJsonPoint point, double maxDistance){
        Query query = new Query();
        query.addCriteria(Criteria.where("coordinate").near(point).maxDistance(maxDistance));
        System.out.println(query.toString());
        List<NaturalDisaster> data = mongoTemplate.find(query,NaturalDisaster.class);
        return data;
    }
    public List<NaturalDisaster> getDisasterWithTypeAndPoint(GeoJsonPoint point, double maxDistance, DisasterType type){
        Query query = new Query();
        query.addCriteria(
                Criteria.where("coordinate").near(point).maxDistance(maxDistance)
                .and("disasterType").is(type)
        );
        System.out.println(query.toString());
        List<NaturalDisaster> data = mongoTemplate.find(query,NaturalDisaster.class);
        return data;
    }
    public List<Account> getAccountNearCoordinates(GeoJsonPoint point,double maxDistance){
        Query query = new Query();
        query.addCriteria(Criteria.where("coordinates").near(point).maxDistance(maxDistance));
        return mongoTemplate.find(query,Account.class);
    }

    public List<RequestRescue> getRequestNear(GeoJsonPoint point,double maxDistance){
        Query query= new Query();
        query.addCriteria(Criteria.where("location").near(point).maxDistance(maxDistance));
        return mongoTemplate.find(query,RequestRescue.class);
    }

}
