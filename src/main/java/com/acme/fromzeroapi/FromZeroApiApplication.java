package com.acme.fromzeroapi;

import com.acme.fromzeroapi.auth.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.auth.domain.model.aggregates.Enterprise;
import com.acme.fromzeroapi.auth.domain.model.aggregates.User;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.DeveloperRepository;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.EnterpriseRepository;
import com.acme.fromzeroapi.auth.infraestructure.persistence.jpa.repositories.UserRepository;
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

	/*@Bean
	CommandLineRunner runner(UserRepository userRepository,
							 DeveloperRepository developerRepository,
							 EnterpriseRepository enterpriseRepository,
							 ProgrammingLanguagesRepository languagesRepository,
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


    /*@Bean
    CommandLineRunner runner(UserRepository userRepository,
							 DeveloperRepository developerRepository,
							 EnterpriseRepository enterpriseRepository,
							 ProgrammingLanguagesRepository languagesRepository,
							 FrameworksRepository frameworksRepository){
        return (String... args)->{
            *//*User user1 = new User("usuario1@gmail.com","contrasena1","D");
            User user2 = new User("usuario2@gmail.com","contrasena2","E");
            userRepository.save(user1);
            userRepository.save(user2);
            Developer dev1 = new Developer(user1, "Jose","Vasquez","descripcion 1","Peru","999999999",0,
					"Specialidades","https://cdn-icons-png.flaticon.com/512/3237/3237472.png");
			Enterprise emp1 = new Enterprise(user2,"geekit","geekit description","Peru","947921342","999999991",
					"website","https://cdn-icons-png.flaticon.com/512/3237/3237472.png","sector 1");
			developerRepository.save(dev1);
			enterpriseRepository.save(emp1);*//*


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
	//borrar luego
	/*@Bean
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
	}*/
}
