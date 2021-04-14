package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.dao.entity.RescueGoods;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RescueGoodRepo extends MongoRepository<RescueGoods,String> {
    List<RescueGoods>  findAllByIdRescuer(String id);
    List<RescueGoods> findAllByArchived(boolean isArchived);
    List<RescueGoods> findAllByNameAreaLike(String name);
}
