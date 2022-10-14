package com.example.md6be.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/login-google")
public class LoginGoogle {

    @GetMapping
    public String welcome(){
        return "welcome google";
    }

    @GetMapping("/user")
    public Principal user(Principal principal){
        System.out.println("user name: " + principal.getName());
        return principal;
    }
}
