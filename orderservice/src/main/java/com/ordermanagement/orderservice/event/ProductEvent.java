package com.ordermanagement.orderservice.event;

public class ProductEvent {

	private Long orderId;
    private String status;  // SUCCESS or FAILED

    public ProductEvent() {
    }

    public ProductEvent(Long orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
