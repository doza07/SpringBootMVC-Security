package com.doza.spring.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/myLoginPage")
    public String showMyLoginPage() {

        return "myLoginPage";
    }

    @GetMapping("/access-denied")
    public String showErrorPage() {

        return "access-denied";
    }
}
