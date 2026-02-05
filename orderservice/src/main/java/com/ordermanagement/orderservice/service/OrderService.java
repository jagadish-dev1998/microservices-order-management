package com.ordermanagement.orderservice.service;

import java.math.BigDecimal;

import com.ordermanagement.orderservice.entity.Order;

public interface OrderService {

	 	Order placeOrder(Long userId, BigDecimal totalAmount);

	    Order getOrderById(Long orderId);

	    Order cancelOrder(Long orderId);
}
