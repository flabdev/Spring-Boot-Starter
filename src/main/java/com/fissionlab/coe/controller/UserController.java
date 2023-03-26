package com.fissionlab.coe.controller;

import com.fissionlab.coe.config.EndPointConfig;
import com.fissionlab.coe.model.User;
import com.fissionlab.coe.repository.UserRepository;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = EndPointConfig.USER_CONFIGURATOR)
@Log4j2
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
    	log.info("Started with get users");
        return userRepository.getAllUsers();
    }

    @GetMapping(value =  EndPointConfig.GET_USER_BY_ID)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        return userRepository.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
