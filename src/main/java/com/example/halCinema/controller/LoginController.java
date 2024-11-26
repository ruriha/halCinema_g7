package com.example.halCinema.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.halCinema.form.LoginForm;
import com.example.halCinema.service.LoginService;
import com.example.halCinema.service.MemberService;
import com.example.halCinema.service.NewsService;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    private final LoginService service;
    private final MemberService MemberService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final NewsService newsService; // NewsServiceのインジェクション

    public LoginController(LoginService service, MemberService MemberService, PasswordEncoder passwordEncoder, MessageSource messageSource, NewsService newsService) {
        this.service = service;
        this.MemberService = MemberService;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
        this.newsService = newsService; // コンストラクタに追加
    }

    @GetMapping("/login")
    public String view(Model model, LoginForm form, HttpSession session) {
        // セッションの無効化
        session.invalidate();

        // newsListを取得し、モデルに追加
        List<Object[]> newsList = newsService.findNewsStreamingDate(); // インスタンスメソッドとして呼び出す
        model.addAttribute("newsList", newsList);

        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, LoginForm form, HttpSession session) {
        var userInfo = service.searchUserById(form.getMemberMailaddress());
        var isCorrectUserAuth = userInfo.isPresent() &&
                passwordEncoder.matches(form.getMemberPassword(), userInfo.get().getMemberPassword());
        
        List<Object[]> users = MemberService.loginEntry(form.getMemberMailaddress(), userInfo.get().getMemberPassword());

        if (!users.isEmpty()) {
            Object[] usersElement = users.get(0);
            UUID userId = (UUID) usersElement[0];
            
            // userId が null でないか確認
            if (userId != null) {
                session.setAttribute("userId", userId);
            }
        }

        if (isCorrectUserAuth) {
            session.setAttribute("loggedInUserEmail", form.getMemberMailaddress());
            return "redirect:/toppage";
        } else {
            return "redirect:/login";
        }
    }
}
