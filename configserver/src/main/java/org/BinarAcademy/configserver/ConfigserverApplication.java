package org.BinarAcademy.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.util.Collections;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConfigserverApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8763"));
		System.out.println("SERVER RUNNING ON PORT 8763 POINTING TO EUREKA SERVER 8761 . . .");
		app.run(args);
	}

}
