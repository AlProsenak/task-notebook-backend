package com.springboot.app;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Task Notebook",
		description = "Demo task notebook application made with Spring Boot.",
		version = "v0.1",
		license = @License(
				name = "Aljaž Prosenak - GitHub",
				url = "https://github.com/ProsenakAljaz/task-notebook-application"
		)
))
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);

		System.out.println("\nApplication started successfully ... \n");
	}

}
