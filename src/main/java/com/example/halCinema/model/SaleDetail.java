package com.example.halCinema.model;


import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "saleDetail")
public class SaleDetail {

    @Id
    @GeneratedValue
    private UUID saleDetailId;

    private Integer quentity;
    
    
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name="saleId")
    private Sale sale;
    
    
    public UUID getSaleDetailId() {
        return saleDetailId;
    }

    public void setSaleDetailId(UUID saleDetailId) {
        this.saleDetailId = saleDetailId;
    }
    
    
    public Integer getQuentity() {
        return quentity;
    }

    public void setQuentity(Integer quentity) {
        this.quentity = quentity;
    }
    
    

    
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
    

}
