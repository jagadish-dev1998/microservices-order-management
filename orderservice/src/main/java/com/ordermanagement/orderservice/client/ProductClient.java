package com.ordermanagement.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ordermanagement.orderservice.dto.ProductDto;

@FeignClient(name="product-service")
public interface ProductClient {

	@GetMapping("/products/{id}")
    ProductDto getProduct(@PathVariable Long id);

    @PutMapping("/products/{id}/reduce-stock")
    void reduceStock(@PathVariable Long id,
                     @RequestParam int quantity);
}
