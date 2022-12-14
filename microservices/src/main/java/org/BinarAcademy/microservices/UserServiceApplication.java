package org.BinarAcademy.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UserServiceApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8762"));
		System.out.println("SERVER RUNNING ON PORT 8762 POINTING TO EUREKA SERVER 8761 . . .");
		app.run(args);
	}

}
