package com.rayco.presentation.mapper;

import com.rayco.domain.entity.User;
import com.rayco.presentation.dto.UserCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapEntityForRegister(UserCreateDTO dto);
}
