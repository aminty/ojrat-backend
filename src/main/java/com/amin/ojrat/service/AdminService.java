package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.exception.DuringSaveException;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    void saveAdmin(AdminParam param) throws Exception;

    Admin findById(Long adminId);

    void createBranch();


    void approveUser(Long userId);


    void BlockUser(Long userId);


    void deleteUser(Long userId);


    void deleteMessage(Long userId);

    boolean isExistsAdminByValue(AdminParam param);








}
