package com.example.halCinema.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.SaleDetail;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, UUID>  {

}
