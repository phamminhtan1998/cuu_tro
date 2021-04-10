package com.phamtan.cuu_tro.dao.custom;

import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import com.phamtan.cuu_tro.dao.entity.NaturalDisaster;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@AllArgsConstructor
public class DisasterMongoQuery {
    private final MongoTemplate mongoTemplate;
    Query query = new Query();
    public List<NaturalDisaster> getWithPoint(GeoJsonPoint point, double maxDistance){
        query.addCriteria(Criteria.where("coordinate").near(point).maxDistance(maxDistance));
        System.out.println(query.toString());
        List<NaturalDisaster> data = mongoTemplate.find(query,NaturalDisaster.class);
        return data;
    }
    public List<NaturalDisaster> getWithTypeAndPoint(GeoJsonPoint point, double maxDistance, DisasterType type){
        query.addCriteria(
                Criteria.where("coordinate").near(point).maxDistance(maxDistance)
                .and("disasterType").is(type)
        );
        System.out.println(query.toString());
        List<NaturalDisaster> data = mongoTemplate.find(query,NaturalDisaster.class);
        return data;
    }
}
