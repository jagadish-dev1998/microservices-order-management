package com.ordermanagement.orderservice.serviceImpl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ordermanagement.orderservice.entity.Order;
import com.ordermanagement.orderservice.entity.Order.OrderStatus;
import com.ordermanagement.orderservice.repo.OrderRepository;
import com.ordermanagement.orderservice.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(Long userId, BigDecimal totalAmount) {

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order cancelOrder(Long orderId) {

        Order order = getOrderById(orderId);

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Order already cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
