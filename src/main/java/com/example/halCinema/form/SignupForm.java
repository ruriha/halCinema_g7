package com.example.halCinema.form;

import java.sql.Date;

import lombok.Data;

@Data
public class SignupForm {

    private String name;
    private String kana;
    private Date birthdate;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String cardNumber;
    private String cardName;
    private Integer cardCvc;
    private Integer cardYear;
    private Integer cardMonth;

    // ゲッターとセッター
}
