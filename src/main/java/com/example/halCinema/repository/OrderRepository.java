package com.example.halCinema.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>  {

}
