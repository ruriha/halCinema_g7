package com.example.halCinema.service;

import org.springframework.stereotype.Service;

import com.example.halCinema.form.SignupForm;
import com.example.halCinema.model.Member;
import com.example.halCinema.repository.MemberRepository;

@Service
public class SignupService {

    private final MemberRepository memberRepository;

    public SignupService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 入力された会員情報を保存する
     * @param form ユーザーが入力したフォームデータ
     */
    public void saveMemberInfo(SignupForm form) {
        // Memberオブジェクトを作成してフォームデータをマッピング
        Member member = new Member();
        member.setMemberName(form.getName());
        member.setMemberNameKana(form.getKana());
        member.setMemberBirthday(form.getBirthdate());
        member.setMemberAddress(form.getAddress());
        member.setMemberTel(form.getPhone());
        member.setMemberMailaddress(form.getEmail());
        member.setMemberPassword(form.getPassword());
        member.setCardNumber(form.getCardNumber());
        member.setCardName(form.getCardName());
        member.setCardCvc(form.getCardCvc());
        member.setCardYear(form.getCardYear());
        member.setCardMonth(form.getCardMonth());
        member.setDelete(false); // デフォルト値として設定

        // リポジトリを通じてデータを保存
        memberRepository.save(member);
    }
}
