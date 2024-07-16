package com.example.halCinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.halCinema.form.SignupForm;
import com.example.halCinema.service.SignupService;

@Controller
public class SignupController {

    private final SignupService service;
    
//    private final PasswordEncoder passwordEncoder;
    
//    private final PasswordEncoder passwordEncoder;
//    
//    private final MessageSource messageSource;

    public SignupController(SignupService service) {
        this.service = service;
//        this.passwordEncoder = passwordEncoder;
//        this.messageSource = messageSource;
    }

    
//    @GetMapping("/signup")
//    public String view(Model model, SignupForm form) {
//        return "signup";
//    }
    
    @GetMapping("/signup")
    public String view(Model model, SignupForm form) {
        return "signup";
    }
    
    
    @PostMapping("/signup")
    public void signup(Model model, SignupForm form) {
    	var userInfo = service.resistUserInfo(form);
    }
}