package com.example.halCinema.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue
    private Integer memberId;

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
    
    
    @OneToMany(mappedBy="member")
    private List<Reservation> reservation;
    
    
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
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
    
    
    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

}
