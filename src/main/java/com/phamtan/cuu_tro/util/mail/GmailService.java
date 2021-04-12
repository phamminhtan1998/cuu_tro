package com.phamtan.cuu_tro.util.mail;

import com.phamtan.base.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GmailService {
    private final EmailService emailService;
    @Async("asyncThreadPool")
    public void sendEmail(){

    }
}
