package com.acme.fromzeroapi;

import com.acme.fromzeroapi.enterprise.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.enterprise.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FromZeroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FromZeroApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200")
						.allowedMethods("*").allowedHeaders("*");
			}
		};
	}

	//borrar luego
	@Bean
	CommandLineRunner runner(EnterpriseRepository enterpriseRepository){
		return (String... args)->{
			List<Enterprise> list= Arrays.asList(
					new Enterprise("Empresa 1"),
					new Enterprise("Empresa 2"),
					new Enterprise("Empresa 3")
			);
			enterpriseRepository.saveAll(list);
		};
	}
}
