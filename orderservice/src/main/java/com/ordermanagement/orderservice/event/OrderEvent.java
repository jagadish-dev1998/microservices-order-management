package com.ordermanagement.orderservice.event;

import java.math.BigDecimal;

public class OrderEvent {

	private Long orderId;
	private Long userId;
	private BigDecimal amount;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUser() {
		return userId;
	}
	public void setUser(Long user) {
		this.userId = user;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public OrderEvent(Long orderId, Long userId, BigDecimal amount) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
	}
	
	public OrderEvent(Long userId, BigDecimal amount) {
		super();
		this.userId = userId;
		this.amount = amount;
	}
	
	public OrderEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
