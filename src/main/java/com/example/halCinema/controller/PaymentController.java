//package com.example.halCinema.controller;
//
//import java.util.Map;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model; // 修正: Spring MVCのModelをインポート
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class PaymentController {
//
//    @PostMapping("/saveData")
//    @ResponseBody
//    public ResponseEntity<?> saveData(@RequestBody Map<String, String> formData, HttpSession session) {
//        // セッションにデータを保存
//        session.setAttribute("formData", formData);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/confirmation")
//    public String showConfirmationPage(HttpSession session, Model model) {
//        // セッションからデータを取得
//        Map<String, String> formData = (Map<String, String>) session.getAttribute("formData");
//
//        if (formData != null) {
//            model.addAttribute("form", formData);
//        }
//        return "confirmation";  // 確認ページのJSPまたはHTMLに遷移
//    }
//}
