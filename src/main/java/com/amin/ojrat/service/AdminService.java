package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.admin.AdminCreationDto;
import com.amin.ojrat.dto.entity.admin.AdminDto;
import com.amin.ojrat.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    void saveAdmin(AdminCreationDto param) throws Exception;

    Admin findById(Long adminId);

    void createBranch();


    void approveUser(Long userId);


    void BlockUser(Long userId);


    void deleteUser(Long userId);


    void deleteMessage(Long userId);

    boolean isExistsAdminByValue(AdminCreationDto param);








}
