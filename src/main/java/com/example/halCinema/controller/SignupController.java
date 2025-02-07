package com.example.halCinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.halCinema.dto.FormData;
import com.example.halCinema.repository.FormDataRepository;
import com.example.halCinema.service.EmailService;
import com.example.halCinema.service.SignupService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
@SessionAttributes({"formData"})
public class SignupController {

    private final SignupService service;
    
    @Autowired
    private FormDataRepository formDataRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;

    public SignupController(SignupService service) {
        this.service = service;
    }

    @ModelAttribute("formData")
    public FormData setupFormData() {
        return new FormData();
    }

    @GetMapping("/input_info")
    public String showInputForm(Model model) {
        FormData formData = new FormData();
        model.addAttribute("formData", formData);
        return "input_info";
    }

    @PostMapping("/input_info")
    public String handleFormSubmission(
        @Valid @ModelAttribute("formData") FormData formData, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "input_info";
        }

        // birthdate をそのまま利用する
        return "redirect:/card";
    }
    
    @GetMapping("/card")
    public String showCardPage(@ModelAttribute("formData") FormData formData, Model model) {
        model.addAttribute("formData", formData); // セッションデータをビューに渡す
        return "card";
    }

    @PostMapping("/card")
    public String processCardPage(@ModelAttribute FormData formData, HttpSession session, Model model) {
        // セッションから既存の入力情報を取得
        FormData existingFormData = (FormData) session.getAttribute("formData");

        // 入力情報とカード情報をマージ
        if (existingFormData != null) {
            if (formData.getCardNumber() != null) existingFormData.setCardNumber(formData.getCardNumber());
            if (formData.getCardName() != null && !formData.getCardName().isEmpty()) {
                existingFormData.setCardName(formData.getCardName());
            }
            if (formData.getCardMonth() != null && formData.getCardYear() != null) {
                existingFormData.setCardMonth(formData.getCardMonth());
                existingFormData.setCardYear(formData.getCardYear());
            }
            if (formData.getCardCvc() != null) existingFormData.setCardCvc(formData.getCardCvc());
        } else {
            existingFormData = formData;
        }

        // セッションに保存
        session.setAttribute("formData", existingFormData);

        // 確認画面にリダイレクト
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String viewConfirmationPage(@ModelAttribute("formData") FormData formData, Model model) {
        model.addAttribute("formData", formData);
        
        return "confirmation"; // 確認ページに遷移
    }

    @PostMapping("/confirmation")
    public String processConfirmation(@ModelAttribute("formData") FormData formData) {
        // パスワードをハッシュ化
        String hashedPassword = passwordEncoder.encode(formData.getPassword());
        formData.setPassword(hashedPassword);

        // データをDBに登録（memberId は自動生成）
        formDataRepository.save(formData);

        // QRコード用のテキスト（UUID をそのまま利用）
        String qrCodeText = formData.getId().toString();

        // 会員登録完了のメールを送信
        emailService.sendAccountCreationEmail(
            formData.getEmail(),
            formData.getName(),
            qrCodeText
        );

        return "redirect:/success";
    }

    @GetMapping("/success")
    public String viewSuccessPage(@ModelAttribute("formData") FormData formData, Model model) {
        model.addAttribute("formData", formData);
        
        return "success"; // 確認ページに遷移
    }
}
