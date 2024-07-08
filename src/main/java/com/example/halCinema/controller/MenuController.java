package com.example.halCinema.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.halCinema.entity.UserInfo;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            UserInfo userInfo = (UserInfo) userDetails;
            model.addAttribute("user", userInfo);
        } else {
            throw new IllegalStateException("UserInfo not found in Authentication or principal is not instance of UserDetails.");
        }
        
        return "menu";
    }
}
