package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import com.phamtan.cuu_tro.dao.entity.NaturalDisaster;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisasterRepo extends MongoRepository<NaturalDisaster,String> {
    List<NaturalDisaster>  findAllByCoordinateNear(GeoJsonPoint point, Distance distance);
    List<NaturalDisaster>  findAllByDisasterType(DisasterType type);
    List<NaturalDisaster>  findAllByDisasterTypeAndCoordinateNear(DisasterType type,GeoJsonPoint point, Distance distance);
}
