package com.amin.ojrat.service.impl;
import com.amin.ojrat.dto.entity.admin.request.AdminCreationDtoParam;
import com.amin.ojrat.dto.mapper.AdminMapper;
import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.exception.DuringSaveException;
import com.amin.ojrat.exception.UserExistsException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.AdminService;
import com.amin.ojrat.service.ServiceRegistry;
import com.amin.ojrat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {


    private final ServiceRegistry serviceRegistry;
    private final DaoRepositories daoRepositories;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl( DaoRepositories daoRepositories, AdminMapper adminMapper, ServiceRegistry serviceRegistry) {
        this.daoRepositories = daoRepositories;
        this.adminMapper = adminMapper;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    @Transactional
    public void saveAdmin(AdminCreationDtoParam param) throws Exception {
        Admin admin = adminMapper.adminCreationDtoToAdmin(param);
        if(!isExistsAdminByValue(param)){
        Branch branch=new Branch();
        branch.setAdmin(admin);
        admin.setBranch(branch);
        daoRepositories.getAdminRepository().save(admin);
        }else {
            throw new UserExistsException("one of this params is taken by another user!");
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
    public boolean isExistsAdminByValue(AdminCreationDtoParam param) {
      return serviceRegistry.getUserService().isUserExistsByValue(
              param.getNationalCode(), param.getEmail(), param.getPhoneNumber()
      );
    }


}
