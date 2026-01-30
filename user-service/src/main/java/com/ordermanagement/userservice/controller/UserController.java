package com.ordermanagement.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.userservice.entity.User;
import com.ordermanagement.userservice.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/users")
public class UserController {
	
	  private static final Logger log =
	            LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	 log.info("DEBUG USER OBJECT: {}", user);
         log.info("USERNAME FIELD: {}", user.getUsername());
    User saveduser = userService.saveUser(user);
        return new ResponseEntity<User>(saveduser,HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<User>>  fetchUsers() {
        	List<User> allUsers = userService.getAllUsers() ;
    	return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

