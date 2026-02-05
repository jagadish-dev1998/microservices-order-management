package com.ordermanagement.orderservice.dto;

public class ProductDto {

	private Long id;
	private String name;
	private Double price;
	private Integer stock;

	public ProductDto(Long id, String name, Double price, Integer stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getStock() {
		return stock;
	}
}
