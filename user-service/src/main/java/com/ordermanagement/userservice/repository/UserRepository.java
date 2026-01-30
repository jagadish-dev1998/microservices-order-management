package com.ordermanagement.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ordermanagement.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
