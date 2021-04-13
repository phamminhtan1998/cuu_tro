package com.phamtan.cuu_tro.util.mail;

import com.phamtan.base.email.data_structure.EmailContentData;
import com.phamtan.base.email.request.EmailRequest;
import com.phamtan.base.email.service.EmailService;
import com.phamtan.base.enumeration.EmailEnum;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class GmailService {
    private final EmailService emailService;
    @Async("asyncThreadPool")
    public void sendEmail(EmailRequest emailRequest){
        emailService.sendEmail(emailRequest);
    }
}
