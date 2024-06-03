package com.acme.fromzeroapi;

import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import com.acme.fromzeroapi.project_branch_deliverables.infrastructure.persistence.jpa.repositories.ProjectRepository;
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


	@Bean
	CommandLineRunner commandLineRunner(ProjectRepository projectRepository) {
		return(String... args)->{
			List<Project> projects = Arrays.asList(
					new Project("Project1"),
					new Project("Project2"),
					new Project("Project3")
			);
			projectRepository.saveAll(projects);
		};
	}

}
