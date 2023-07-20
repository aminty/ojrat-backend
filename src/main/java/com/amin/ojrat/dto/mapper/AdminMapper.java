package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.entity.Admin;


public interface AdminMapper {

    AdminParam adminToAdminParam(Admin admin);

    Admin adminParamToAdmin(AdminParam param);

}
