package com.example.halCinema.service;

import org.dozer.DozerBeanMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.halCinema.entity.UserInfo;
import com.example.halCinema.form.SignupForm;
import com.example.halCinema.model.Member;
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

    public Member saveMember(Member member) {
        // Dozerを使用してMemberクラスからUserInfoクラスへのマッピングを行う場合
        var userInfo = mapper.map(member, UserInfo.class);
        
        // 他の必要な処理（例：パスワードのエンコードなど）
        var encodedPassword = passwordEncoder.encode(member.getMemberPassword());
        userInfo.setMemberPassword(encodedPassword);
        
        // UserInfoを保存し、必要に応じてMemberクラスに戻す
        var savedUserInfo = repository.save(userInfo);
        return mapper.map(savedUserInfo, Member.class);
    }
    
}
