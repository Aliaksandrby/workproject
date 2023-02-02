package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final static String usernameError = "username exists";
    private final static String confirmError = "wrong confirm password";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean isPasswordEqualsToConfirmPassword(User user) {
        return !user.getPassword().equals(user.getConfirmPassword());
    }

    @Override
    public boolean hasUsernameIntoDB(User user) {
        return !(userRepository.findByUsername(user.getUsername()) == null);
    }


    @Override
    public User editUser(User user, Long id, Long roleId) {
        User editUser = userRepository.findById(id).get();
        if(!editUser.getUsername().equals(user.getUsername())) {
            if(hasUsernameIntoDB(user)) {
                editUser.setMessage(usernameError);
                return editUser;
            }
        }
        if(isPasswordEqualsToConfirmPassword(user)) {
            editUser.setMessage(confirmError);
            return editUser;
        }
        editUser.setUsername(user.getUsername());
        editUser.setPassword(passwordEncoder.encode(user.getPassword()));
        editUser.setRoleSet(Set.of(roleRepository.findById(1L).get()));
        return editUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}