package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.user.response.UserLoginDtoResult;
import com.amin.ojrat.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserLoginDtoResult userToUserLoginResultDto(User user);
}
