package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.repository.AdminRepository;
import com.amin.ojrat.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    AdminRepository adminRepository;

    @Override
    public void saveAdmin(Admin admin) {
        //TODO
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
