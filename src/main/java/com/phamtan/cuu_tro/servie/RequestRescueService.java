package com.phamtan.cuu_tro.servie;

import com.phamtan.base.email.data_structure.EmailContentData;
import com.phamtan.base.email.request.EmailRequest;
import com.phamtan.base.enumeration.EmailEnum;
import com.phamtan.cuu_tro.common.enumeration.AccountType;
import com.phamtan.cuu_tro.common.enumeration.DangerLevel;
import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.custom.CustomMongoQuery;
import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.dao.entity.RequestRescue;
import com.phamtan.cuu_tro.dao.repo.RequestRescueRepo;
import com.phamtan.cuu_tro.util.mail.GmailService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class RequestRescueService {
    private final RequestRescueRepo requestRescueRepo;
    private final CustomMongoQuery customMongoQuery;
    private final GmailService gmailService;


    public List<RequestRescue> getAll() {
        return requestRescueRepo.findAll();
    }

    public List<RequestRescue> getByLevel(DangerLevel level) {
        return requestRescueRepo.findAllByLevelOrderByTimeDesc(level);
    }

    public List<RequestRescue> getByLocation(GeoJsonPoint point, double distance) {
        return customMongoQuery.getRequestNear(point, distance);
    }

    public List<RequestRescue> getByPerson(String personId) {
        return requestRescueRepo.findByIdPerson(personId);
    }

    public RequestRescue create(RequestRescue requestRescue) {
//        Create new thread for notify all account in range 10km  from the request rescue location
        new Thread(() ->{
//            Regex patter for email
            Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
            String emailFrom = System.getenv("EMAIL_FROM");
            List<Account> accounts = customMongoQuery.getAccountNearCoordinates(requestRescue.getCoordinates(), 10000.0);
            if(!accounts.isEmpty()){
                EmailContentData emailContentData = EmailContentData.builder()
                        .key(EmailEnum.TEXT)
                        .name("message")
                        .data("Có yêu cầu cứu hộ mới : "+requestRescue.getDescription())
                        .build();
                EmailContentData content = EmailContentData.builder()
                        .key(EmailEnum.TEXT)
                        .name("message")
                        .data("Xảy ra tại khu vực : "+requestRescue.getLocation())
                        .build();
                EmailRequest emailRequest = new EmailRequest();
                emailRequest.setFrom(emailFrom);

                emailRequest.setSubject("Test email");
                emailRequest.setContent(Arrays.asList(emailContentData,content));
                accounts.forEach(account -> {
                    if (account.getAccountType().equals(AccountType.GMAIL)){
                        emailRequest.setTo(account.getAccountIdf());
                        gmailService.sendEmail(emailRequest);
                    }
                });
            }
        }).start();

        return requestRescueRepo.save(requestRescue);
    }

    public RequestRescue update(RequestRescue requestRescue) {
        if (requestRescueRepo.existsById(requestRescue.getId())) {
            return requestRescueRepo.save(requestRescue);
        }
        return null;
    }

    public StatusBasic delete(RequestRescue requestRescue) {
        if (requestRescueRepo.existsById(requestRescue.getId())) {
            requestRescueRepo.delete(requestRescue);
            return StatusBasic.SUCCESSFUL;
        }
        return StatusBasic.ERROR;
    }
}
