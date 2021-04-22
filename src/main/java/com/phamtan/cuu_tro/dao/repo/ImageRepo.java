package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.dao.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends MongoRepository<Image,String> {
}
