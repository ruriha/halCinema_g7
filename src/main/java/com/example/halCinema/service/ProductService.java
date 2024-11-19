package com.example.halCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
    
	@Autowired
	ProductRepository ProductRepository;
    
	
	//  すべての商品情報を取得
    public List<Object[]> findAllProduct() {
        return ProductRepository.findAllProduct();
    }

}
