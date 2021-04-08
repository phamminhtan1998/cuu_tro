package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.common.enumeration.DangerLevel;
import com.phamtan.cuu_tro.dao.entity.RequestRescue;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRescueRepo extends MongoRepository<RequestRescue,String> {
    List<RequestRescue> findAllByLevelOrderByTimeDesc(DangerLevel level);
    List<RequestRescue> findAllByLocationNearOrderByTimeDesc(GeoJsonPoint point , Distance distance);
    List<RequestRescue> findByIdPerson(String id);
}
