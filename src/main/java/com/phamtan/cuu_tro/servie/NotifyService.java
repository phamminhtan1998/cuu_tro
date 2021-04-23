package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.Notify;
import com.phamtan.cuu_tro.dao.repo.NotifyRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class NotifyService {
    private final NotifyRepo notifyRepo;
    public List<Notify> getAll(){
        return notifyRepo.findAll();
    }
    public Notify create(Notify notify){
        return notifyRepo.save(notify);
    }
    public Notify update(Notify notify){
        return notifyRepo.save(notify);
    }
    public StatusBasic delete(Notify notify){
        try {
            notifyRepo.delete(notify);
            return StatusBasic.SUCCESSFUL;
        }catch (Exception e){
            log.error(e.getMessage());
            return StatusBasic.ERROR;
        }
    }

}
