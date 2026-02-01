package com.ordermanagement.product_service.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import com.ordermanagement.product_service.entity.Product;

public interface ProductService {

	 Product createProduct(Product product);

	Page<Product> getProducts(String name, int page, int size);
	
	Page<Product> searchProduct(String name,Pageable pageable);
	
	void updateStock(Long productId, int quantity);

}
