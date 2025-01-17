package com.example.halCinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.halCinema.form.SignupForm;
import com.example.halCinema.service.SignupService;

@Controller
public class SignupController {

    private final SignupService service;

    public SignupController(SignupService service) {
        this.service = service;
    }

    @GetMapping("/input_info")
    public String viewInputInfo(Model model, SignupForm form) {
        return "input_info";
    }

    @PostMapping("/input_info")
    public String goToCardPage(@ModelAttribute SignupForm form, Model model) {
        // input_infoからcardに遷移
        return "redirect:/card"; // card.htmlにリダイレクト
    }

    @GetMapping("/card")
    public String viewCardPage(Model model, SignupForm form) {
        return "card";
    }

    @PostMapping("/card")
    public String saveMemberInfo(@ModelAttribute SignupForm form, Model model) {
        // 必要な処理を実行
        model.addAttribute("form", form); // 必要に応じてデータを渡す
        return "card";
    }

    @PostMapping("/configration")
    public String processPayment(@ModelAttribute SignupForm form, Model model) {
        // 決済情報を処理するロジックを追加（必要に応じて）
        model.addAttribute("form", form); // 必要に応じてデータを渡す
        return "redirect:/configration"; // configration.html にリダイレクト
    }

    @GetMapping("/configration")
    public String viewConfigrationPage(Model model) {
        return "confirmation";  // 修正：configration -> confirmation
    }
    
    @PostMapping("/save")
    public String saveMemberData(@ModelAttribute SignupForm form, Model model) {
        // データ保存の処理を実行
        service.saveMemberInfo(form); // SignupServiceに保存処理を委譲
        model.addAttribute("message", "会員情報が保存されました。");

        // 保存完了画面にリダイレクト
        return "redirect:/success"; // 完了画面に遷移
    }
    
    @GetMapping("/success")
    public String viewSuccess(Model model) {
        return "success";
    }


}
