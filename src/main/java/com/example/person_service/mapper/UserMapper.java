package com.example.person_service.mapper;


import com.example.common.UserDto;
import com.example.person_service.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @InheritInverseConfiguration
    User map(UserDto userDto);

    UserDto map (User user);
}
