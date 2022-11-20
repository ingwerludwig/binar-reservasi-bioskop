package org.BinarAcademy.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class DiscoveryserverApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DiscoveryserverApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8010"));
		System.out.println("SERVER RUNNING ON PORT 8004 . . .");
		app.run(args);
	}

}
