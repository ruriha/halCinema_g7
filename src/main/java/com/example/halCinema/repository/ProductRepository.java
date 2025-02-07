package com.example.halCinema.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>  {
	
	//  すべての食品商品情報を取得
    @Query("select p.productId, p.productName, p.price, p.productImg from product p where p.productCategory = 1")
	List<Object[]> findAllFoodProduct();
	//  すべての飲料商品情報を取得
    @Query("select p.productId, p.productName, p.price, p.productImg from product p where p.productCategory = 2")
	List<Object[]> findAllDrinkProduct();
	//  すべてのグッズ商品情報を取得
    @Query("select p.productId, p.productName, p.price, p.productImg from product p where p.productCategory = 3")
	List<Object[]> findAllGoodsProduct();
	
	//  商品ID取得
	@Query("select p.productId " +
	           "from product p " +
	           "where p.productName = ?1")
	List<Object[]> findProductId(String productName);

}
