package com.example.halCinema.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID>  {
	
	
	//  注文ID取得
	@Query("select s.saleId " +
	           "from sale s " +
	           "where s.saleDatetime = ?1")
	List<Object[]> findSaleId(LocalDateTime saleDatetime);

}
