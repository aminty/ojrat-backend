package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.dto.mapper.AdminMapper;
import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.repository.AdminRepository;
import com.amin.ojrat.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public void saveAdmin(AdminParam param) {
        //TODO
        Admin admin = adminMapper.adminParamToAdmin(param);
        adminRepository.save(admin);

    }

    @Override
    public Admin findById(Long adminId) {
        //TODO
        return null;
    }

    @Override
    public void createBranch() {
        //TODO
    }

    @Override
    public void approveUser(Long userId) {
        //TODO
    }

    @Override
    public void BlockUser(Long userId) {
        //TODO
    }

    @Override
    public void deleteUser(Long serId) {
        //TODO
    }

    @Override
    public void deleteMessage(Long userId) {
        //TODO
    }


}
