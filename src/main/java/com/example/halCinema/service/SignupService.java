package com.example.halCinema.service;

import org.dozer.DozerBeanMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.halCinema.entity.UserInfo;
import com.example.halCinema.form.SignupForm;
import com.example.halCinema.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {
    
    private final UserInfoRepository repository;
    
    private final DozerBeanMapper mapper; // DozerBeanMapperをDIする
    private final PasswordEncoder passwordEncoder; // PasswordEncoderをDIする
    
    public UserInfo resistUserInfo(SignupForm form){
        var userInfo = mapper.map(form, UserInfo.class); // Dozerを使用してマッピングする
        
        var encodedPassword = passwordEncoder.encode(form.getMemberPassword());
        userInfo.setMemberPassword(encodedPassword); // 修正されたメソッド呼び出し
        
        return repository.save(userInfo);
    }
}
