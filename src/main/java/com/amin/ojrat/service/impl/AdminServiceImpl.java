package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.dto.mapper.IAdminMapper;
import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.exception.DuringSaveException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.AdminService;
import com.amin.ojrat.service.BranchService;
import com.amin.ojrat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final DaoRepositories daoRepositories;
    private final IAdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(UserService userService, DaoRepositories daoRepositories, IAdminMapper adminMapper) {
        this.userService = userService;
        this.daoRepositories = daoRepositories;
        this.adminMapper = adminMapper;
    }

    @Override
    @Transactional
    public void saveAdmin(AdminParam param) throws Exception {
        Admin admin = adminMapper.adminParamToAdmin(param);
        Branch branch=new Branch();
        branch.setAdmin(admin);
        admin.setBranch(branch);
        Admin savedAdmin = daoRepositories.getAdminRepository().save(admin);
        if (savedAdmin.getId()==null){
            throw new DuringSaveException("new record doesn't save");
        }

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
