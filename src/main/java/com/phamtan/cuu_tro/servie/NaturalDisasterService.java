package com.phamtan.cuu_tro.servie;

import com.phamtan.base.email.data_structure.EmailContentData;
import com.phamtan.base.email.request.EmailRequest;
import com.phamtan.base.enumeration.EmailEnum;
import com.phamtan.cuu_tro.common.enumeration.AccountType;
import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.custom.CustomMongoQuery;
import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.dao.entity.NaturalDisaster;
import com.phamtan.cuu_tro.dao.repo.DisasterRepo;
import com.phamtan.cuu_tro.util.mail.GmailService;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class NaturalDisasterService {
    private final DisasterRepo disasterRepo;
    private final CustomMongoQuery customMongoQuery;
    private final GmailService gmailService;

    public List<NaturalDisaster> getAll(){
        return disasterRepo.findAll();
    }
    public NaturalDisaster getById(String id){
        return disasterRepo.findById(id).get();
    }
    public List<NaturalDisaster> getAllNear(GeoJsonPoint point, double distanceByKM){
        Distance distance = new Distance(distanceByKM, Metrics.MILES);
       return customMongoQuery.getDisasterWithPoint(point,distanceByKM);
    }
    public List<NaturalDisaster> getAllByDisasterType(DisasterType type){
        return disasterRepo.findAllByDisasterType(type);
    }
    public List<NaturalDisaster> getAllByDisasterTypeAndDistance(GeoJsonPoint point,double distance,DisasterType type){
        Distance distance1 = new Distance(distance, Metrics.KILOMETERS);
        return customMongoQuery.getDisasterWithTypeAndPoint(point,distance,type);
    }

    public NaturalDisaster create(NaturalDisaster naturalDisaster){
        //        Create new thread for notify all account in range 10km  from the request rescue location
        new Thread(() ->{
            String emailFrom = System.getenv("EMAIL_FROM");
            List<Account> accounts = customMongoQuery.getAccountNearCoordinates(naturalDisaster.getCoordinate(), 10000.0);
            if(!accounts.isEmpty()){
                EmailContentData emailContentData = EmailContentData.builder()
                        .key(EmailEnum.TEXT)
                        .name("message")
                        .data("Có thiet hai moi tai  : "+naturalDisaster.getArea())
                        .build();
                EmailContentData content = EmailContentData.builder()
                        .key(EmailEnum.TEXT)
                        .name("message")
                        .data("Xảy ra tại khu vực : "+naturalDisaster.getDisasterType().getMessage())
                        .build();
                EmailRequest emailRequest = new EmailRequest();
                emailRequest.setFrom(emailFrom);

                emailRequest.setSubject("Test email");
                emailRequest.setContent(Arrays.asList(emailContentData,content));
                accounts.forEach(account -> {
                    if (account.getAccountType().equals(AccountType.GMAIL)){
                        System.out.println(account.getAccountIdf());
                        emailRequest.setTo(account.getAccountIdf());
                        gmailService.sendEmail(emailRequest);
                    }
                });
            }
        }).start();
      return   disasterRepo.save(naturalDisaster);
    }
    public NaturalDisaster update(NaturalDisaster naturalDisaster){
        if(disasterRepo.existsById(naturalDisaster.getId())){
            return   disasterRepo.save(naturalDisaster);
        }
        return null;

    }
    public StatusBasic delete(NaturalDisaster naturalDisaster){
        if(disasterRepo.existsById(naturalDisaster.getId())){
               disasterRepo.delete(naturalDisaster);
               return StatusBasic.SUCCESSFUL;
        }
        return StatusBasic.ERROR;
    }
}
