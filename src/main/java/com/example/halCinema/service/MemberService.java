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
    private MemberRepository memberRepository;

    // 会員情報をIDで検索
    public Member findMemberById(UUID memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    // 会員情報取得
    public List<Object[]> findReservationMember(UUID memberId) {
        return memberRepository.findReservationMember(memberId);
    }

    // 会員のメールアドレス取得
    public List<Object[]> findMailaddress(UUID memberId) {
        return memberRepository.findMailaddress(memberId);
    }

    // ログイン
    public List<Object[]> loginEntry(String memberMailaddress, String memberPassword) {
        return memberRepository.loginEntry(memberMailaddress, memberPassword);
    }
    
//	//  会員検索
//    public List<Object[]> findMember(String memberName, String memberTel) {
//        return MemberRepository.findMember(memberName, memberTel);
//    }
    
    
    
    
    

    // 会員情報保存 (新規または更新)
    public void saveMemberInfo(Member member) {
        memberRepository.save(member);
    }
}
