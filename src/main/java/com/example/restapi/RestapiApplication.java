package com.example.restapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load(); // load the .env file.
		SpringApplication.run(RestapiApplication.class, args);
	}

}
