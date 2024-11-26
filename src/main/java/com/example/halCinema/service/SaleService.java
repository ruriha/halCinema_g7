package com.example.halCinema.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Member;
import com.example.halCinema.model.Sale;
import com.example.halCinema.repository.SaleRepository;

@Service
@Transactional
public class SaleService {
    
	@Autowired
	SaleRepository SaleRepository;
	
	//  注文時に必要
    public Sale findSaleById(UUID saleId) {
        return SaleRepository.findById(saleId).orElse(null);
    }

    //  注文登録
    public Sale saveSale(LocalDateTime saleDatetime, Member member) {
    	Sale sale = new Sale();
    	sale.setSaleDatetime(saleDatetime);
    	sale.setMember(member);
        return SaleRepository.save(sale);
    }
    
	
	//  注文ID取得
    public List<Object[]> findSaleId(LocalDateTime saleDatetime) {
        return SaleRepository.findSaleId(saleDatetime);
    }

}
