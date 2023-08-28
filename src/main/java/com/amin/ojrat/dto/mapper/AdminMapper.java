package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.admin.request.AdminCreationDtoParam;
import com.amin.ojrat.entity.Admin;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AdminMapper {

     Admin adminCreationDtoToAdmin(AdminCreationDtoParam adminDto);




}
