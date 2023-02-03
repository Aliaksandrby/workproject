package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {

    public static final String loginPassError = "wrong login or password";
    private final static String confirmError = "wrong confirm password";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean isPasswordEqualsToConfirmPassword(User user) {
        return !user.getPassword().equals(user.getConfirmPassword());
    }

    public User editUser(User user) {
        User editUser = userRepository.findById(1L).get();
        if(isPasswordEqualsToConfirmPassword(user)) {
            editUser.setMessage(confirmError);
            return editUser;
        }
        editUser.setUsername(user.getUsername());
        editUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        editUser.setRoleSet(Set.of(roleRepository.findById(1L).get()));
        return editUser;
    }

    public User getUser() {
        return userRepository.findById(1L).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}