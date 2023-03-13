package com.fissionlab.coe.controller;

import com.fissionlab.coe.model.User;
import com.fissionlab.coe.repository.UserRepository;
import com.fissionlab.coe.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
    	LOGGER.info("Started with get users");
        return userRepository.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        return userRepository.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
