package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.dao.entity.NewWay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewWayRepo extends MongoRepository<NewWay,String> {
}
