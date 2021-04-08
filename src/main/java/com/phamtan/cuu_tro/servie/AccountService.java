package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.dao.repo.AccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AccountService {
    private final AccountRepo accountRepo;
    public  Account getAccountByAccountIdf(String  accountIdf){
        return accountRepo.findByAccountIdf(accountIdf);
    }
    public Account getAccountFullName(String  fullName){
        return accountRepo.findByFullName(fullName);
    }

    public List<Account> getAccountNear(GeoJsonPoint point,double distanceByKM){
        Point destinationPoint= new Point();
        destinationPoint.setLocation(point.getX(),point.getY());
        Distance distanceTmp  = new Distance(distanceByKM, Metrics.KILOMETERS);
        List<Account> accountList = accountRepo.findAllByCoordinatesNear(point,distanceTmp);
        return  accountList;
    }
    public List<Account> getAccountByDob(LocalDate date,Long minusDay){
        List<Account> accounts = accountRepo.findAllByDob(date.minusDays(minusDay));
        return accounts;
    }

    public Account create(Account account){
        if(accountRepo.findByAccountIdf(account.getAccountIdf())!=null){
         return null;
        }
      return  accountRepo.save(account);
    }
    public Account update(Account account){
        if(accountRepo.findByAccountIdf(account.getAccountIdf())!=null){
            return  accountRepo.save(account);
        }
        return null;
    }
    public StatusBasic delete(Account account){
        if(accountRepo.findByAccountIdf(account.getAccountIdf())!=null){
              accountRepo.delete(account);
              return StatusBasic.SUCCESSFUL;
        }
        return StatusBasic.ERROR;
    }


}
