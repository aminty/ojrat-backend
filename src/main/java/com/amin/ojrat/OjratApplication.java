package com.amin.ojrat;

import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Message;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.enums.Role;
import com.amin.ojrat.repository.MessageRepository;
import com.amin.ojrat.repository.UserRepository;
import com.amin.ojrat.service.AdminService;
import com.amin.ojrat.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class OjratApplication {


    public static void main(String[] args) {
        SpringApplication.run(OjratApplication.class, args);
    }




}