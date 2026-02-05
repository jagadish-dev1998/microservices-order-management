package com.ordermanagement.orderservice.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalAmount;
    
    public enum OrderStatus {
        CREATED,
        CONFIRMED,
        CANCELLED
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Order(Long id, Long userId, OrderStatus status, BigDecimal totalAmount) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalAmount=" + totalAmount + "]";
	}
    
    
}
