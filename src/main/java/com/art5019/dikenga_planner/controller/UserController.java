package com.art5019.dikenga_planner.controller;

import com.art5019.dikenga_planner.dto.UserRegisterDTO;
import com.art5019.dikenga_planner.model.User;
import com.art5019.dikenga_planner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService us;

    @PostMapping
    public User registerUser(@RequestBody UserRegisterDTO urdto) {
        return us.registerUser(urdto);
    }
}
