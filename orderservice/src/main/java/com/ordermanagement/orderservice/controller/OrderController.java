package com.ordermanagement.orderservice.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.orderservice.entity.Order;
import com.ordermanagement.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestParam Long userId,
            @RequestParam BigDecimal totalAmount) {

        Order order = orderService.placeOrder(userId, totalAmount);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {

        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
