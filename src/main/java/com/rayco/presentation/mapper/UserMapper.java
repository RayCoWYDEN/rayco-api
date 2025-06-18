package com.rayco.presentation.mapper;

import com.rayco.domain.entity.User;
import com.rayco.presentation.dto.UserCreateDTO;
import com.rayco.presentation.dto.UserInfoDTO;
import com.rayco.presentation.response.TokenResponse;
import com.rayco.presentation.response.UserLoggedResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapEntityForRegister(UserCreateDTO dto);

    UserLoggedResponse mapUserLoggedResponse(User user, TokenResponse token);

    User toEntity(UserInfoDTO userInfoDTO);
}
