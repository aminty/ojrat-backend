package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.admin.AdminCreationDto;
import com.amin.ojrat.dto.entity.admin.AdminDto;
import com.amin.ojrat.entity.Admin;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AdminMapper {



     Admin adminCreationDtoToAdmin(AdminCreationDto adminDto);




}
