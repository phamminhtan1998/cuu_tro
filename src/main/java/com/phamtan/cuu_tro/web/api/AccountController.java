package com.phamtan.cuu_tro.web.api;

import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.servie.AccountService;
import com.phamtan.cuu_tro.util.GeoJsonConvert;
import com.phamtan.cuu_tro.web.dto.request.AccountReqDto;
import com.phamtan.cuu_tro.web.dto.request.GeoJsonDTO;
import com.phamtan.cuu_tro.web.dto.response.AccountResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    public final ModelMapper modelMapper;
    public  final AccountService accountService;

    @GetMapping
    public List<AccountResponse> getAll(){
        List<Account> data =accountService.getAllAccounts();
        List<AccountResponse> responses = new ArrayList<>();
        for(Account account : data){
            AccountResponse accountResponse = new AccountResponse();
            modelMapper.map(account,accountResponse);
            responses.add(accountResponse);
        }
        return responses;
    }
    @PostMapping("/near")
    public List<AccountResponse> getAllNear(@Valid @RequestBody GeoJsonDTO geoJsonDTO){
        GeoJsonPoint point = GeoJsonConvert.convertLatLonToGeoPoint(geoJsonDTO.getLat(), geoJsonDTO.getLon());
        List<Account> data =accountService.getAccountNear(point, geoJsonDTO.getDistance());
        List<AccountResponse> responses = new ArrayList<>();
        for(Account account : data){
            AccountResponse accountResponse = new AccountResponse();
            modelMapper.map(account,accountResponse);
            responses.add(accountResponse);
        }
        return responses;

    }
    @GetMapping("/fullName/{fullName}")
    public List<AccountResponse> getByUsername(@PathVariable("fullName")String fullName){
        List<Account> data = accountService.getAccountFullName(fullName);
       List<AccountResponse> responses = new ArrayList<>();
        for(Account account : data){
            AccountResponse accountResponse = new AccountResponse();
           modelMapper.map(account,accountResponse);
           responses.add(accountResponse);
       }
        return responses;
    }
    @GetMapping("/accountIdf/{idf}")
    public AccountResponse getByAccountIdf(@PathVariable("idf")String idf){
        AccountResponse accountResponse = new AccountResponse();
        Account account = accountService.getAccountByAccountIdf(idf);
        modelMapper.map(account,accountResponse);
       return accountResponse;
    }


    @PostMapping
    public Account create(@Valid  @RequestBody AccountReqDto account){
        Account accountDes = new Account();
        modelMapper.map(account,accountDes);
        accountDes.setCoordinates(GeoJsonConvert.convertLatLonToGeoPoint(
                account.getLon(),
                account.getLat()
        ));
        return accountService.create(accountDes);
    }
    @PatchMapping
    public Account update(@Valid @RequestBody AccountReqDto account){
        Account accountDes = new Account();
        modelMapper.map(account,accountDes);
        accountDes.setCoordinates(GeoJsonConvert.convertLatLonToGeoPoint(
                account.getLon(),
                account.getLat()
        ));
        return accountService.update(accountDes);
    }
    @DeleteMapping
    public String delete(@Valid @RequestBody AccountReqDto account){
        Account accountDes = new Account();
        modelMapper.map(account,accountDes);
        accountDes.setCoordinates(GeoJsonConvert.convertLatLonToGeoPoint(
                account.getLon(),
                account.getLat()
        ));
       try {
           accountService.update(accountDes);
           return "delete successful";
       }
       catch (Exception ex){
           ex.printStackTrace();
           return "wrong ";
       }
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
