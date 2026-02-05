package com.ordermanagement.product_service.repo;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ordermanagement.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByIdForUpdate(@Param("id") Long id);

}
