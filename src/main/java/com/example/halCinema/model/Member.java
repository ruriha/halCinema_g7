package com.example.halCinema.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue
    private UUID memberId;

    private String memberName;

    private String memberNameKana;

    private Date memberBirthday;

    private String memberAddress;

    private String memberTel;

    private String memberMailaddress;

    private Date memberJoinday;

    private Date memberUpdateday;

    private Boolean memberStatus;

    private String memberPassword;

    private String cardNumber;

    private String cardName;

    private Integer cardCvc;

    private Integer cardYear;

    private Integer cardMonth;

    private Boolean delete;
    
    
    @OneToMany(mappedBy="member")
    private List<Reservation> reservation;
    
    
    public UUID getMemberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }
    
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    public String getMemberNameKana() {
        return memberNameKana;
    }

    public void setMemberNameKana(String memberNameKana) {
        this.memberNameKana = memberNameKana;
    }
    
    public Date getMemberBirthday() {
        return memberBirthday;
    }

    public void setMemberBirthday(Date memberBirthday) {
        this.memberBirthday = memberBirthday;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }
    
    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }
    
    public String getMemberTel() {
        return memberTel;
    }

    public void setMemberMailaddress(String memberMailaddress) {
        this.memberMailaddress = memberMailaddress;
    }
    
    public String getMemberMailaddress() {
        return memberMailaddress;
    }
    
    public Date getMemberJoinday() {
        return memberJoinday;
    }

    public void setMemberJoinday(Date memberJoinday) {
        this.memberJoinday = memberJoinday;
    }
    
    public Date getMemberUpdateday() {
        return memberUpdateday;
    }

    public void setMemberUpdateday(Date memberUpdateday) {
        this.memberUpdateday = memberUpdateday;
    }
    
    public Boolean getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Boolean memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
    
    public String getMemberPassword() {
        return memberPassword;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    
    public String getCardName() {
        return cardName;
    }

    public void setCardCvc(Integer cardCvc) {
        this.cardCvc = cardCvc;
    }
    
    public Integer getCardCvc() {
        return cardCvc;
    }

    public void setCardYear(Integer cardYear) {
        this.cardYear = cardYear;
    }
    
    public Integer getCardYear() {
        return cardYear;
    }

    public void setCardMonth(Integer cardMonth) {
        this.cardMonth = cardMonth;
    }
    
    public Integer getCardMonth() {
        return cardMonth;
    }
    
    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
    
    
    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

}
