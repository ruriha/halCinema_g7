package com.example.halCinema.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Product;
import com.example.halCinema.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
    
	@Autowired
	ProductRepository ProductRepository;
	
	//  注文時に必要
    public Product findProductById(UUID productId) {
        return ProductRepository.findById(productId).orElse(null);
    }
    
	
	//  すべての食品商品情報を取得
    public List<Object[]> findAllFoodProduct() {
        return ProductRepository.findAllFoodProduct();
    }	
	//  すべての飲料商品情報を取得
    public List<Object[]> findAllDrinkProduct() {
        return ProductRepository.findAllDrinkProduct();
    }	
	//  すべてのグッズ商品情報を取得
    public List<Object[]> findAllGoodsProduct() {
        return ProductRepository.findAllGoodsProduct();
    }

    
	//  商品ID取得
    public List<Object[]> findProductId(String productName) {
        return ProductRepository.findProductId(productName);
    }
}
