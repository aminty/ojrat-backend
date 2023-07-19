package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.admin.AdminParam;
import com.amin.ojrat.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface AdminMapper {

    AdminParam adminToAdminParam(Admin admin);

    Admin adminParamToAdmin(AdminParam param);

}
