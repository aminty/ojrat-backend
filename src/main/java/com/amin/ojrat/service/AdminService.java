package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.admin.request.AdminCreationDtoParam;
import com.amin.ojrat.entity.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    void saveAdmin(AdminCreationDtoParam param) throws Exception;

    Admin findById(Long adminId);

    void createBranch();

    void approveUser(Long userId);

    void BlockUser(Long userId);

    void deleteUser(Long userId);

    void deleteMessage(Long userId);

    boolean isExistsAdminByValue(AdminCreationDtoParam param);








}
