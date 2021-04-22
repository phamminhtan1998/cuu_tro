package com.phamtan.cuu_tro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@ComponentScan(basePackages ={"com.phamtan.cuu_tro","com.example.grpc_base","com.phamtan.base","io.github.logger"} )
@EnableMongoAuditing
public class CuuTroApplication  {

    public static void main(String[] args) {
        SpringApplication.run(CuuTroApplication.class, args);
    }


}
