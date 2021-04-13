package com.phamtan.cuu_tro.web.api;

import com.phamtan.base.email.data_structure.EmailContentData;
import com.phamtan.base.email.request.EmailRequest;
import com.phamtan.base.email.service.EmailService;
import com.phamtan.base.enumeration.EmailEnum;
import com.phamtan.cuu_tro.util.mail.GmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final GmailService gmailService;
    @GetMapping
    public String test(){
//        gmailService.sendEmail();
        return "ok ";
    }
}
