package com.ordermanagement.orderservice.serviceImpl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.orderservice.entity.Order;
import com.ordermanagement.orderservice.entity.Order.OrderStatus;
import com.ordermanagement.orderservice.event.OrderEvent;
import com.ordermanagement.orderservice.kafka.OrderProducer;
import com.ordermanagement.orderservice.repo.OrderRepository;
import com.ordermanagement.orderservice.service.OrderService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    private OrderProducer orderProducer;
    
    @Override
    public Order placeOrder(Long userId, BigDecimal totalAmount) {

    	//log.info("Placing order for userId={}", userId);
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.CREATED);
        
        Order savedOrder = orderRepository.save(order);
        
        OrderEvent event = new OrderEvent(
        		savedOrder.getId(), 
        		savedOrder.getUserId(),
        		savedOrder.getTotalAmount()
        );

        orderProducer.sendOrderEvent(event);
        
        return savedOrder;
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
