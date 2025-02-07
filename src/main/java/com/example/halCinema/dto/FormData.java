package com.example.halCinema.dto;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class FormData {

    @Id
    @GeneratedValue(generator = "UUID") // UUID 自動生成
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)") // MySQL の場合
    private UUID memberId;

    private String memberName;
    private String memberNameKana;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBirthday;

    private String memberTel;
    private String memberAddress;
    private String memberMailaddress;
    private String memberPassword;

    private String cardNumber;
    private String cardName;
    private Integer cardCvc;
    private Integer cardYear;
    private Integer cardMonth;

    // ゲッターとセッター
    public UUID getId() {
        return memberId;
    }

    public void setId(UUID id) {
        this.memberId = id;
    }

    public String getName() {
        return memberName;
    }

    public void setName(String name) {
        this.memberName = name;
    }

    public String getKana() {
        return memberNameKana;
    }

    public void setKana(String kana) {
        this.memberNameKana = kana;
    }

    public Date getBirthdate() {
        return memberBirthday;
    }

    public void setBirthdate(Date birthdate) {
        this.memberBirthday = birthdate;
    }

    public String getPhone() {
        return memberTel;
    }

    public void setPhone(String phone) {
        this.memberTel = phone;
    }

    public String getAddress() {
        return memberAddress;
    }

    public void setAddress(String address) {
        this.memberAddress = address;
    }

    public String getEmail() {
        return memberMailaddress;
    }

    public void setEmail(String email) {
        this.memberMailaddress = email;
    }

    public String getPassword() {
        return memberPassword;
    }

    public void setPassword(String password) {
        this.memberPassword = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardCvc() {
        return cardCvc;
    }

    public void setCardCvc(Integer cardCvc) {
        this.cardCvc = cardCvc;
    }

    public Integer getCardYear() {
        return cardYear;
    }

    public void setCardYear(Integer cardYear) {
        this.cardYear = cardYear;
    }

    public Integer getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(Integer cardMonth) {
        this.cardMonth = cardMonth;
    }
}
