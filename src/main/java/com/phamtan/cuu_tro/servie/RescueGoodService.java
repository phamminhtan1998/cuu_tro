package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.RescueGoods;
import com.phamtan.cuu_tro.dao.repo.RescueGoodRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RescueGoodService {
    private final RescueGoodRepo rescueGoodRepo;

    public List<RescueGoods> getAll(){
        return rescueGoodRepo.findAll();
    }
    public List<RescueGoods> getByIdRescuer(String idRescuer){
        return rescueGoodRepo.findAllByIdRescuer(idRescuer);
    }
    public RescueGoods  getById(String id){
        return rescueGoodRepo.findById(id).get();
    }
    public List<RescueGoods> getByNotArchived( ){
        return rescueGoodRepo.findAllByArchived(false);
    }
    public List<RescueGoods> getByArchived( ){
        return rescueGoodRepo.findAllByArchived(true);
    }
    public List<RescueGoods> getByNameArea(String nameArea ){
        return rescueGoodRepo.findAllByNameAreaLike(nameArea);
    }

    public RescueGoods create(RescueGoods rescueGoods){
        return rescueGoodRepo.save(rescueGoods);
    }
    public RescueGoods update(RescueGoods rescueGoods){
        if(rescueGoodRepo.existsById(rescueGoods.getId())){
            return rescueGoodRepo.save(rescueGoods);
        }
        return null;
    }
    public StatusBasic delete(RescueGoods rescueGoods){
        if(rescueGoodRepo.existsById(rescueGoods.getId())){
             rescueGoodRepo.delete(rescueGoods);
             return StatusBasic.SUCCESSFUL;
        }
        return StatusBasic.ERROR;
    }
}
