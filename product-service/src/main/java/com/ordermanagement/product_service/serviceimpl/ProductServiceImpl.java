package com.ordermanagement.product_service.serviceimpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ordermanagement.product_service.dto.ProductRequestDTO;
import com.ordermanagement.product_service.entity.Product;
import com.ordermanagement.product_service.exception.InsufficientStockException;
import com.ordermanagement.product_service.repo.ProductRepository;
import com.ordermanagement.product_service.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired ProductRepository productRepository;
	
	@Override
	@Transactional
	public Product createProduct(ProductRequestDTO product) {
		
		Product products = new Product();
	    products.setName(product.getName());
	    products.setPrice(product.getPrice());
	    products.setStock(product.getStock());

		return productRepository.save(products);
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

	@Transactional
    public void reduceStock(Long productId, int quantity) {

        Product product = productRepository.findByIdForUpdate(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setStock(product.getStock() - quantity);
    }

	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void updateStock(Long productId, int quantity) {
		// TODO Auto-generated method stub
		
	}
	
	

}
