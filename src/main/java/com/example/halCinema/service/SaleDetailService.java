package com.example.halCinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Product;
import com.example.halCinema.model.Sale;
import com.example.halCinema.model.SaleDetail;
import com.example.halCinema.repository.SaleDetailRepository;

@Service
@Transactional
public class SaleDetailService {
    
	@Autowired
	SaleDetailRepository SaleDetailRepository;

    public SaleDetail saveSaleDetail(Integer quentity, Product product, Sale sale) {
    	SaleDetail saleDetail = new SaleDetail();
    	saleDetail.setQuentity(quentity);
    	saleDetail.setProduct(product);
    	saleDetail.setSale(sale);
        return SaleDetailRepository.save(saleDetail);
    }

}
