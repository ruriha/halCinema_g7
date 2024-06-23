package com.example.halCinema.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.halCinema.controller.QRCodeGenerator;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSender emailSender;

    public void sendQRCodeEmail(String recipientEmail, String subject, String qrCodeText, String movieTitle, String strScreeningDatetime, String screenName, String memberName) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setTo(recipientEmail);
            helper.setSubject(subject);

            // HTML形式のメール本文を設定
            String text = String.format(
                "<html><body>" +
                "こんにちは、%s様<br><br>" +
                "以下の映画の予約が完了しました。<br><br>" +
                "予約ID: %s<br>" +
                "映画タイトル: %s<br>" +
                "上映日時: %s<br>" +
                "スクリーン: %s<br><br>" +
                "QRコードを添付ファイルとして送信しましたので、当日こちらをご提示ください。<br><br>" +
                "ご利用ありがとうございます。" +
                "</body></html>",
                memberName, qrCodeText, movieTitle, strScreeningDatetime, screenName
            );

            helper.setText(text, true); // HTML形式のメール本文を設定

            // QRコードを生成
            byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(qrCodeText);

            // QRコードを添付
            if (qrCodeImage != null) {
                helper.addAttachment("qrcode.png", new ByteArrayResource(qrCodeImage));
            }

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
