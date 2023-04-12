package com.app.spring.security.jwt.security.services;

import com.app.spring.security.jwt.dto.UserDTO;
import com.app.spring.security.jwt.mapper.UserMapper;
import com.app.spring.security.jwt.models.User;
import com.app.spring.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        //return UserMapper.INSTANCE.usersToUsersDTO(userRepository.findAll());
        return userRepository.findAll();
    }
}
