package com.example.halCinema.model;


import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "orderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue
    private UUID orderDetailId;

    private Integer quentity;
    
    
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name="orderId")
    private Order order;
    
    
    public UUID getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(UUID orderDetailId) {
        this.orderDetailId = orderDetailId;
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
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    

}
