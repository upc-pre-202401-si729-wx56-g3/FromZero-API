package com.acme.fromzeroapi;

import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.developer_branch_projects.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import com.acme.fromzeroapi.enterprise_branch_projects.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.enterprise_branch_projects.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Framework;
import com.acme.fromzeroapi.projects.domain.model.aggregates.ProgrammingLanguage;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.FrameworksRepository;
import com.acme.fromzeroapi.projects.infrastructure.persistence.jpa.repositories.ProgrammingLanguagesRepository;
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
	CommandLineRunner runner(EnterpriseRepository enterpriseRepository,
							 DeveloperRepository developerRepository,
							 ProgrammingLanguagesRepository languagesRepository,
							 FrameworksRepository frameworksRepository){
		return (String... args)->{
			List<Enterprise> list= Arrays.asList(
					new Enterprise("Empresa 1"),
					new Enterprise("Empresa 2"),
					new Enterprise("Empresa 3")
			);
			List<Developer> devList=Arrays.asList(
					new Developer("Developer 1"),
					new Developer("Developer 2"),
					new Developer("Developer 3")
			);
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
			developerRepository.saveAll(devList);
			enterpriseRepository.saveAll(list);
		};
	}
}
