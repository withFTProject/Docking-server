package com.lgcns.Docking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockingApplication.class, args);
	}

}


