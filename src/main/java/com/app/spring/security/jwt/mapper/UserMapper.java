package com.app.spring.security.jwt.mapper;

import com.app.spring.security.jwt.dto.UserDTO;
import com.app.spring.security.jwt.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    //List<UserDTO> usersToUsersDTO(List<User> users);
}
