package org.BinarAcademy.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryserverApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DiscoveryserverApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8761"));
		System.out.println("SERVER RUNNING ON PORT 8761 . . .");
		app.run(args);
	}

}
