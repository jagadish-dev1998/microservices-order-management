package com.ordermanagement.product_service.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ordermanagement.product_service.dto.ProductRequestDTO;
import com.ordermanagement.product_service.entity.Product;

public interface ProductService {

	 Product createProduct(ProductRequestDTO product);

	Page<Product> getProducts(String name, int page, int size);
	
	Page<Product> searchProduct(String name,Pageable pageable);
	
	void updateStock(Long productId, int quantity);

	Optional<Product> getProductById(Long id);

}
