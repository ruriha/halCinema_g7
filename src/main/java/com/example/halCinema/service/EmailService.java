package com.example.halCinema.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    public void sendAccountCreationEmail(String recipientEmail, String memberName, String memberId) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setTo(recipientEmail);
            helper.setSubject("【会員登録完了】QRコードのご案内");

            // HTML形式のメール本文を設定
            String text = String.format(
                "<html><body>" +
                "こんにちは、%s様<br><br>" +
                "このたびは、当サービスにご登録いただきありがとうございます。<br><br>" +
                "以下はあなたの会員情報です。<br><br>" +
                "会員ID: %s<br><br>" +
                "会員IDのQRコードを添付いたしましたので、大切に保管してください。<br><br>" +
                "今後ともよろしくお願いいたします。" +
                "</body></html>",
                memberName, memberId
            );

            helper.setText(text, true); // HTML形式のメール本文を設定

            // QRコードを生成
            byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(memberId);

            // QRコードを添付
            if (qrCodeImage != null) {
                helper.addAttachment("member_qr.png", new ByteArrayResource(qrCodeImage));
            }

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

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

    public void sendBoughtEmail(String recipientEmail, String subject, String memberName, String saleCodeText, Map<String, Object> orderData) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            String saleDetailText = "";
            int total = 0;
		    for (Map.Entry<String, Object> entry : orderData.entrySet()) {
			    String productName = entry.getKey();
	            Map<String, Object> details = (Map<String, Object>) entry.getValue();
	            int quantity = (int) details.get("quantity");
	            int price = (int) details.get("price");
	            saleDetailText = saleDetailText + "商品名： " + productName + "<br>" +
	            				 "個数： " + quantity + "点<br>" +
	            				 "値段： " + price + "円<br><br>" +
	            				 "--------------------------------------------<br><br>";
	            total = total + price * quantity;
	        }

            // HTML形式のメール本文を設定
            String text = String.format(
                "<html><body>" +
                "こんにちは、%s様<br><br>" +
                "お買い上げ明細をお届けいたします。<br><br>" +
                "購入ID: %s<br><br>" +
				"--------------------------------------------<br><br>" +
                "%s" +
                "合計： %s円<br><br>"+
                "ご利用ありがとうございます。" +
                "</body></html>",
                memberName, saleCodeText, saleDetailText, total
            );

            helper.setText(text, true); // HTML形式のメール本文を設定

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
