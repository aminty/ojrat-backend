package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.dto.mapper.AdminMapper;
import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.AdminService;
import com.amin.ojrat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final DaoRepositories daoRepositories;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(UserService userService, DaoRepositories daoRepositories, AdminMapper adminMapper) {
        this.userService = userService;
        this.daoRepositories = daoRepositories;
        this.adminMapper = adminMapper;
    }

    @Override
    @Transactional
    public void saveAdmin(AdminParam param) {
        Admin admin = adminMapper.adminParamToAdmin(param);
        admin.setBranch(new Branch());
        daoRepositories.getAdminRepository().save(admin);

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

    @Override
    public boolean isExistsAdminByValue(AdminParam param) {
      return userService.isUserExistsByValue(param.getNationalCode(), param.getEmail(), param.getPhoneNumber());
    }


}
