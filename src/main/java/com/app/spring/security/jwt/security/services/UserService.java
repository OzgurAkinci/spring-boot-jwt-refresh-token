package com.app.spring.security.jwt.security.services;

import com.app.spring.security.jwt.constant.AppConstant;
import com.app.spring.security.jwt.models.User;
import com.app.spring.security.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        //return UserMapper.INSTANCE.usersToUsersDTO(userRepository.findAll());
        return userRepository.findAll();
    }

    public Map<String, Object> findAllWithPaging(int page, String sortBy, String name) {
        Pageable pagingSort = PageRequest.of(page-1, AppConstant.globalPageSize, Sort.by(sortBy).descending());
        Page<User> users;
        if (name == null || name.isEmpty()){
            users = userRepository.findAll(pagingSort);
        }else {
            users = userRepository.findByUsernameContaining(name, pagingSort);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("users", users.get().toList());
        response.put("currentPage", users.getNumber() + 1);
        response.put("totalItems", users.getTotalElements());
        response.put("totalPages", users.getTotalPages());
        response.put("pageSize", users.getSize());
        return response;
    }
}
