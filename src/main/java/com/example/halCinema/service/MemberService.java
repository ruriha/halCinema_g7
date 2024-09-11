package com.example.halCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Member;
import com.example.halCinema.repository.MemberRepositoty;

@Service
@Transactional
public class MemberService {
	
    @Autowired
    MemberRepositoty MemberRepositoty;
    
    //  座席予約時に必要
    public Member findMemberById(Integer memberId) {
        return MemberRepositoty.findById(memberId).orElse(null);
    }
    
	
	//  会員情報取得
    public List<Object[]> findReservationMember(Integer memberId) {
        return MemberRepositoty.findReservationMember(memberId);
    }
    
	//  会員のメールアドレス取得
    public List<Object[]> findMailaddress(Integer memberId) {
        return MemberRepositoty.findMailaddress(memberId);
    }
    
	//  ログイン
    public List<Object[]> loginEntry(String memberMailaddress, String memberPassword) {
        return MemberRepositoty.loginEntry(memberMailaddress, memberPassword);
    }
    
    

    
}
