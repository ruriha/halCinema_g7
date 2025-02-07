package com.example.halCinema.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID memberId;

    private String memberName;

    private String memberNameKana;

    @Temporal(TemporalType.DATE)
    private Date memberBirthday;

    private String memberAddress;

    private String memberTel;

    private String memberMailaddress;

    @Temporal(TemporalType.TIMESTAMP)
    private Date memberJoinday;

    @Temporal(TemporalType.TIMESTAMP)
    private Date memberUpdateday;

    private Boolean memberStatus;

    private String memberPassword;

    private String cardNumber;

    private String cardName;

    private Integer cardCvc;

    private Integer cardYear;

    private Integer cardMonth;

    private Boolean delete;

    @OneToMany(mappedBy = "member")
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

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberTel() {
        return memberTel;
    }

    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel;
    }

    public String getMemberMailaddress() {
        return memberMailaddress;
    }

    public void setMemberMailaddress(String memberMailaddress) {
        this.memberMailaddress = memberMailaddress;
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

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardCvc() {
        return cardCvc;
    }

    public void setCardCvc(Integer cardCvc) {
        this.cardCvc = cardCvc;
    }

    public Integer getCardYear() {
        return cardYear;
    }

    public void setCardYear(Integer cardYear) {
        this.cardYear = cardYear;
    }

    public Integer getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(Integer cardMonth) {
        this.cardMonth = cardMonth;
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
