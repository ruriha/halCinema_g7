//package com.example.halCinema.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.example.halCinema.entity.UserInfo;
//import com.example.halCinema.repository.UserInfoRepository;
//import com.example.halCinema.repository.UserRepository;
//
//@Controller
//public class UserController {
//
//    private final UserRepository userRepository;
//
//    // コンストラクタを通じてUserRepositoryのインスタンスをDI（Dependency Injection）で受け取る
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    private UserInfoRepository userInfoRepository;
//
//    @GetMapping("/menu")
//    public String userProfile(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserInfo) {
//            UserInfo userInfo = (UserInfo) auth.getPrincipal();
////            String memberMailaddress = userInfo.getMemberMailaddress();
//            
//            model.addAttribute("memberMailaddress", userInfo.getMemberMailaddress());
//            model.addAttribute("role", "ROLE_USER");
//        }
////        } else {
////            model.addAttribute("memberMailaddress", "未認証ユーザー");
////            model.addAttribute("role", "ROLE_ANONYMOUS");
////        }
//
//        return "menu";
//    }
//
//}
