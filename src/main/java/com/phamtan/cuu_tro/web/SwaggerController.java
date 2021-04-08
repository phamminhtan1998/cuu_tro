package com.phamtan.cuu_tro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping("/swagger")
    public String redirectSwaggerUi(){
        return "redirect:/swagger-ui.html";
    }
}
