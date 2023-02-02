package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    String loginPassError = "wrong login or password";
    boolean isPasswordEqualsToConfirmPassword(User user);
    boolean hasUsernameIntoDB(User user);
    User editUser(User user,Long id, Long roleId);
}
