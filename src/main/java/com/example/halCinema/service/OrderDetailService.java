package com.example.halCinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Order;
import com.example.halCinema.model.OrderDetail;
import com.example.halCinema.model.Product;
import com.example.halCinema.repository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailService {
    
	@Autowired
	OrderDetailRepository OrderDetailRepository;

    public OrderDetail saveOrderDetail(Integer quentity, Product product, Order order) {
    	OrderDetail orderDetail = new OrderDetail();
    	orderDetail.setQuentity(quentity);
    	orderDetail.setProduct(product);
    	orderDetail.setOrder(order);
        return OrderDetailRepository.save(orderDetail);
    }

}
