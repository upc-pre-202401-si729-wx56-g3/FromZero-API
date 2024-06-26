package com.acme.fromzeroapi;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.FrameworksRepository;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProgrammingLanguagesRepository;
import com.acme.fromzeroapi.usermanagement.domain.model.aggregates.User;
import com.acme.fromzeroapi.usermanagement.infraestructure.persistence.jpa.repositories.UserRepository;
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

	/*@Bean
	CommandLineRunner runner(ProgrammingLanguagesRepository languagesRepository,
							 FrameworksRepository frameworksRepository){
		return (String... args)->{
			List<ProgrammingLanguage> languageList=Arrays.asList(
					new ProgrammingLanguage("Javascript"),
					new ProgrammingLanguage("Typescript"),
					new ProgrammingLanguage("HTML"),
					new ProgrammingLanguage("CSS")
			);
			List<Framework> frameworksList = Arrays.asList(
					new Framework("Vue Js"),
					new Framework("Angular"),
					new Framework("React")
			);
			languagesRepository.saveAll(languageList);
			frameworksRepository.saveAll(frameworksList);
		};
	}*/
}
