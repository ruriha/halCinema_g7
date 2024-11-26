package com.example.halCinema.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "sale")
public class Sale {
	
    @Id
    @GeneratedValue
    private UUID saleId;

    private LocalDateTime saleDatetime;
    
    
    @ManyToOne
    @JoinColumn(name="memberId")
    private Member member;
    
    
    @OneToMany(mappedBy="sale")
    private List<SaleDetail> saleDetail;
    
    
    public UUID getSaleId() {
        return saleId;
    }

    public void setSaleId(UUID saleId) {
        this.saleId = saleId;
    }
    
    
    public LocalDateTime getSaleDatetime() {
        return saleDatetime;
    }

    public void setSaleDatetime(LocalDateTime saleDatetime) {
        this.saleDatetime = saleDatetime;
    }
    
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    
    
    public List<SaleDetail> getSaleDetail() {
        return saleDetail;
    }

    public void setSaleDetail(List<SaleDetail> saleDetail) {
        this.saleDetail = saleDetail;
    }
    

}
