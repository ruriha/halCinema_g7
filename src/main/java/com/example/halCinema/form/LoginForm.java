package com.example.halCinema.form;

import lombok.Data;

@Data
public class LoginForm {
	
	private String memberMailaddress;
	
	private String memberPassword;

//    private String username;
//    private String password;

    // ゲッターとセッター
    public String getUsername() {
        return memberMailaddress;
    }

//    public void setUsername(String username) {
//        this.memberMailaddress = username;
//    }

    public String getPassword() {
        return memberPassword;
    }
//
//    public void setPassword(String password) {
//        this.memberPassword = password;
//    }
	
}
