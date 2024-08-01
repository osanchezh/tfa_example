package com.buffer.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.buffer.security.model.auth.RegisterRequest;
import com.buffer.security.service.AuthenticationService;

import static com.buffer.security.model.user.Role.ADMIN;
import static com.buffer.security.model.user.Role.MANAGER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	/*
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
		
			RegisterRequest admin = new RegisterRequest();
			admin.setFirstname("Admin");
			admin.setLastname("Admin");
			admin.setEmail("admin@mail.com");
			admin.setPassword("password");
			admin.setRole(ADMIN);
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			RegisterRequest manager = new RegisterRequest();
			manager.setFirstname("Admin");
			manager.setLastname("Admin");
			manager.setEmail("manager@mail.com");
			manager.setPassword("password");
			manager.setRole(MANAGER);
			
			System.out.println("Manager token: " + service.register(manager).getAccessToken());
			

		};
	}*/
}
