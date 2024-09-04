package com.example.halCinema.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.halCinema.constant.ErrorMessageConst;
import com.example.halCinema.form.LoginForm;
import com.example.halCinema.service.LoginService;
import com.example.halCinema.utill.AppUtill;

@Controller
public class LoginController {

    private final LoginService service;
    
//    private final PasswordEncoder passwordEncoder;
    
    private final PasswordEncoder passwordEncoder;
    
    private final MessageSource messageSource;

    public LoginController(LoginService service, PasswordEncoder passwordEncoder, MessageSource messageSource) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
    }

    
    @GetMapping("/login")
    public String view(Model model, LoginForm form) {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(Model model, LoginForm form) {
        // フォームのデータをコンソールに出力
//        System.out.println("Login ID: " + form.getLoginId());
//        System.out.println("Password: " + form.getPassword());

        var userInfo = service.searchUserById(form.getMemberMailaddress());
        
        var encordedPassword = passwordEncoder.encode(form.getMemberPassword());
        
        var isCorrectUserAuth = userInfo.isPresent()
//        		&& form.getPassword().equals(userInfo.get().getPassword());
                && passwordEncoder.matches(form.getMemberPassword(), userInfo.get().getMemberPassword());
        
        if (isCorrectUserAuth) {
            return "redirect:/menu";
        } else {
        	var errorMsg = AppUtill.getMessage(messageSource, ErrorMessageConst.LOGIN_WRONG_INPUT);
        	
            model.addAttribute("errorMsg", errorMsg);
            return "login";
        }
    }
}
