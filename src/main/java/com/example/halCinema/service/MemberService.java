package com.example.halCinema.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Member;
import com.example.halCinema.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	
    @Autowired
    MemberRepository MemberRepository;
    
    //  座席予約時に必要
    public Member findMemberById(UUID memberId) {
        return MemberRepository.findById(memberId).orElse(null);
    }
    
	
	//  会員情報取得
    public List<Object[]> findReservationMember(UUID memberId) {
        return MemberRepository.findReservationMember(memberId);
    }
    
	//  会員のメールアドレス取得
    public List<Object[]> findMailaddress(UUID memberId) {
        return MemberRepository.findMailaddress(memberId);
    }
    
	//  ログイン
    public List<Object[]> loginEntry(String memberMailaddress, String memberPassword) {
        return MemberRepository.loginEntry(memberMailaddress, memberPassword);
    }
    
	//  会員検索
    public List<Object[]> findMember(String memberName, String memberTel) {
        return MemberRepository.findMember(memberName, memberTel);
    }
    
    
    
    
    

    
}
