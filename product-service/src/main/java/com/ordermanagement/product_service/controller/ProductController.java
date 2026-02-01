package com.ordermanagement.product_service.controller;

import java.awt.print.Pageable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.product_service.entity.Product;
import com.ordermanagement.product_service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired ProductService productService;

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
		
		Product createProduct = null;
		try {
			createProduct = productService.createProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(createProduct,HttpStatus.OK) ;
	}
	
	//Get Products with Pagination
	@GetMapping
	public ResponseEntity<Page<Product>> getProducts(@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		
		Page<Product> fetchedProdcts = productService.getProducts(name,page,size);
		return new ResponseEntity<Page<Product>>(fetchedProdcts,HttpStatus.OK);
	}
	
	// Search Products (uses INDEX on name)
	@GetMapping("/search")
	public ResponseEntity<Page<Product>> searchProduct(@RequestParam String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size){
		
		Pageable pageable = (Pageable) PageRequest.of(page, size);
		Page<Product> products = productService.searchProduct(name, pageable);
		return new ResponseEntity<Page<Product>>(products,HttpStatus.OK);
		
	}
	
	//Update Stock (Transactional)
    @PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(
            @PathVariable Long id,
            @RequestParam int quantity) {

        productService.updateStock(id, quantity);
        return ResponseEntity.ok("Stock updated successfully");
    }
}
