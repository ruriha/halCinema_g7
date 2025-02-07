package com.example.halCinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.halCinema.model.Member;
import com.example.halCinema.repository.MemberRepository;

@Service
public class SignupService {

    private final MemberRepository memberRepository;

    @Autowired
    public SignupService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void saveMemberInfo(Member member) {
        memberRepository.save(member);
    }
}
