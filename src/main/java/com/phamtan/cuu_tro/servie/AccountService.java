package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.custom.CustomMongoQuery;
import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.dao.repo.AccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Distance;
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
    private final CustomMongoQuery customMongoQuery;
    public List<Account> getAllAccounts(){

        return accountRepo.findAll();
    }

    public  Account getAccountByAccountIdf(String  accountIdf){
        return accountRepo.findByAccountIdf(accountIdf);
    }
    public List<Account> getAccountFullName(String  fullName){
        return accountRepo.findByFullName(fullName);
    }

    public List<Account> getAccountNear(GeoJsonPoint point,double distanceByKM){

        List<Account> accountList = customMongoQuery.getAccountNearCoordinates(point,distanceByKM*1000);
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
