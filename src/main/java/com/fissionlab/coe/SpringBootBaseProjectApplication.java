package com.fissionlab.coe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBaseProjectApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootBaseProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBaseProjectApplication.class, args);
		LOGGER.info("<<<================================  SPRING BOOT BASE PROJECT SERVER STARTED AND RUNNING ================================ >>>");
	}

}
