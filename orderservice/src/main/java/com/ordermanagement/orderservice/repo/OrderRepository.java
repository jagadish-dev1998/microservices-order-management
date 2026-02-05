package com.ordermanagement.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ordermanagement.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
 
}
