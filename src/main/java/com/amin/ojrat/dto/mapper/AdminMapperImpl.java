package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.entity.Admin;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl  implements AdminMapper{
    @Override
    public  AdminParam adminToAdminParam(Admin admin) {
        AdminParam param=new AdminParam();
        param.setFirstName(admin.getFirstName());
        param.setLastName(admin.getLastName());
        param.setEmail(admin.getEmail());
        param.setNationalCode(admin.getNationalCode());
        param.setPassword(admin.getPassword());
        param.setAddress(admin.getAddress());
        param.setRoles(admin.getRoles());
        return param;
    }

    @Override
    public Admin adminParamToAdmin(AdminParam param) {
        Admin admin=new Admin();
        admin.setFirstName(param.getFirstName());
        admin.setLastName(param.getLastName());
        admin.setPassword(param.getPassword());
        admin.setNationalCode(param.getNationalCode());
        admin.setAddress(param.getAddress());
        admin.setEmail(param.getEmail());
        admin.setRoles(param.getRoles());
        return admin;
    }
}
