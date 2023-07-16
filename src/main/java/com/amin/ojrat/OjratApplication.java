package com.amin.ojrat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OjratApplication {


    public static void main(String[] args) {
        SpringApplication.run(OjratApplication.class, args);
    }




}