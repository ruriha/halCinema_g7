package com.example.halCinema.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.halCinema.entity.UserInfo;
import com.example.halCinema.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final UserInfoRepository repository;

    public Optional<UserInfo> searchUserById(String memberMailaddress){
        return repository.findById(memberMailaddress);
    }

    // authenticate メソッドを追加
    public boolean authenticate(String memberMailaddress, String memberPassword) {
        Optional<UserInfo> userInfo = repository.findById(memberMailaddress);
        if (userInfo.isPresent()) {
            return userInfo.get().getMemberPassword().equals(memberPassword);
        }
        return false;
    }
}
