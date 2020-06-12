package com.banepali;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMain {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class,args);
	}
	// in order to run this 
	// http://localhost:8080/dogs/11
	// "http://localhost:8080" --> this is the url pattern and 
	// then "/dogs/11" --> path variable mapping from the controller
}
