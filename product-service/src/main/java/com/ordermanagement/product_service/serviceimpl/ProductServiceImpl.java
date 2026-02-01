package com.ordermanagement.product_service.serviceimpl;

import java.awt.print.Pageable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ordermanagement.product_service.entity.Product;
import com.ordermanagement.product_service.repo.ProductRepository;
import com.ordermanagement.product_service.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired ProductRepository productRepository;
	
	@Override
	@Transactional
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public Page<Product> getProducts(String name, int page, int size) {
		Pageable pageable = (Pageable) PageRequest.of(page, size,Sort.by("price").descending());
		return productRepository.findByNameContainingIgnoreCase(name,pageable);
	}

	@Override
	public Page<Product> searchProduct(String name, Pageable pageable) {
		return productRepository.findByNameContainingIgnoreCase(name, pageable);
	}

	@Override
	@Transactional
	public void updateStock(Long productId, int quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		product.setStock(product.getStock() - quantity);
	}
	
	

}
