package com.example.halCinema.model;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private UUID productId;

    private String productName;

    private Integer price;

    private Integer productCategory;
    
    
    @OneToMany(mappedBy="product")
    private List<SaleDetail> orderDetail;
    
    
    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
    
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    
    public Integer getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }
    
    
    
    
    public List<SaleDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<SaleDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
    

}
