package com.phamtan.cuu_tro.web.api;

import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.servie.AccountService;
import com.phamtan.cuu_tro.util.GeoJsonConvert;
import com.phamtan.cuu_tro.web.dto.request.AccountReqDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    public final ModelMapper modelMapper;
    public  final AccountService accountService;

    @PostMapping
    public Account create(@RequestBody AccountReqDto account){
        Account accountDes = new Account();
        modelMapper.map(account,accountDes);
        accountDes.setCoordinates(GeoJsonConvert.convertLatLonToGeoPoint(
                account.getLat(),
                account.getLon()
        ));
//        return accountService.create(account);
        return accountDes;
    }
    @GetMapping
    public String test(){
        return "test";
    }


}
