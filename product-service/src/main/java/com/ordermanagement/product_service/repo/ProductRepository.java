package com.ordermanagement.product_service.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ordermanagement.product_service.dto.ProductRequestDTO;
import com.ordermanagement.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

	Optional<Product> findByIdForUpdate(Long productId);

}
