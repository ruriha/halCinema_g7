package com.example.halCinema.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Member;
import com.example.halCinema.model.Order;
import com.example.halCinema.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
    
	@Autowired
	OrderRepository OrderRepository;

    public Order saveOrder(LocalDateTime orderDatetime, Member member) {
    	Order order = new Order();
    	order.setOrderDatetime(orderDatetime);
    	order.setMember(member);
        return OrderRepository.save(order);
    }

}
