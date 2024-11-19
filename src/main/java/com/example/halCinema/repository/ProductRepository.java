package com.example.halCinema.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>  {
	
	//  すべての商品情報を取得
    @Query("select p.productId, p.productName, p.price from product p")
	List<Object[]> findAllProduct();

}
