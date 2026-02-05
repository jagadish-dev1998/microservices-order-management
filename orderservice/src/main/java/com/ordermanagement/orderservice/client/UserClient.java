package com.ordermanagement.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ordermanagement.orderservice.dto.UserDto;

@FeignClient(name = "user-service")
public interface UserClient {
	
	@GetMapping("/users/{id}")
	UserDto getUserById(@PathVariable Long id);
}
