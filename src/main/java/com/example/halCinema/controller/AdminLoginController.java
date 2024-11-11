package com.example.halCinema.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.halCinema.form.AdminLoginForm;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    // 複数のログインIDとパスワードを保持するMapを定義
    private static final Map<String, String> CREDENTIALS = new HashMap<>();
    
    static {
        // IDとパスワードのペアを追加
        CREDENTIALS.put("admin1", "pwd1");
        CREDENTIALS.put("admin2", "pwd2");
        CREDENTIALS.put("admin3", "pwd3");
    }

    private final HttpSession session;

    public AdminLoginController(HttpSession session) {
        this.session = session;
    }

    // ログインページを表示するためのGETリクエスト処理を追加
    @GetMapping("/mng_login")
    public String showLoginForm(Model model) {
        model.addAttribute("adminloginForm", new AdminLoginForm());
        return "mng_login";
    }

    @PostMapping("/mng_login")
    public String processLogin(@ModelAttribute("adminloginForm") AdminLoginForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "mng_login";
        }

        // マップで認証を行う
        String loginId = form.getLoginId();
        String password = form.getPassword();
        if (CREDENTIALS.containsKey(loginId) && CREDENTIALS.get(loginId).equals(password)) {
            // 認証成功時、セッションにログインIDを保存
            session.setAttribute("loggedInUserId", loginId);
            return "redirect:/data1";
        } else {
            model.addAttribute("loginError", "ユーザーIDまたはパスワードが間違っています");
            return "mng_login";
        }
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        String loggedInUserId = (String) session.getAttribute("loggedInUserId");
        model.addAttribute("loggedInUserId", loggedInUserId);
        return "menu";
    }
}
