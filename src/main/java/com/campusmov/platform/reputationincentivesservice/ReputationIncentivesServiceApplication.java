package com.campusmov.platform.reputationincentivesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReputationIncentivesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReputationIncentivesServiceApplication.class, args);
	}

}
