package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserEditController {

    @Autowired
    private UserService userService;

    @GetMapping("/editUser")
    @Secured(value = {"ROLE_ADMIN"})
    public String editUser(Model model) {
        model.addAttribute("user",userService.getUser());
        return "EditUser";
    }

    @PostMapping("/editUser")
    @Secured(value = {"ROLE_ADMIN"})
    public String editUser(User user, Model model) {
        model.addAttribute("user",userService.editUser(user));
        return "EditUser";
    }
}
