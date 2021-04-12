package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.dao.entity.NewWay;
import com.phamtan.cuu_tro.dao.repo.NewWayRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewWayService {
    private final NewWayRepo newWayRepo;

    public NewWay create(NewWay newWay){
        return  newWayRepo.save(newWay);
    }
}
