package com.doza.spring.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/leaders")
    public String homeManager() {

        return "leaders";
    }

    @GetMapping("/systems")
    public String homeAdmins() {

        return "admin";
    }
}
