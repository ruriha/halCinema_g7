package com.example.halCinema.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID>  {

}
