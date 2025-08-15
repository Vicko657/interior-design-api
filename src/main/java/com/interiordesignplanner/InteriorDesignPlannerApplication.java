package com.interiordesignplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enables auditing
public class InteriorDesignPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteriorDesignPlannerApplication.class, args);
	}

}
