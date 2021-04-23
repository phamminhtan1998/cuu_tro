package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.dao.entity.Notify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyRepo extends MongoRepository<Notify,String> {
}
