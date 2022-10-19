package org.BinarAcademy.Challenge_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;


import java.net.URI;
import java.util.Collections;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@SpringBootApplication
@Configuration
public class Challenge4Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Challenge4Application.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8002"));
		System.out.println("SERVER RUNNING ON PORT 8004 . . .");
		app.run(args);
	}

}
