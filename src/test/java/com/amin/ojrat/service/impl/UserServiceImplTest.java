package com.amin.ojrat.service.impl;

import com.amin.ojrat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    void itShouldMaskPhone() {

        String phone = "09389501261";
        int firstFrom=0;
        int firstEnd=4;
        int secondFrom=7;
        int secondEnd=11;
        String result = phone.substring(firstFrom, firstEnd) + "***" + phone.substring(secondFrom, secondEnd);
        System.out.println(result);
    }
}