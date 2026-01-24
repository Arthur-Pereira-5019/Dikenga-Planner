package com.art5019.dikenga_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
