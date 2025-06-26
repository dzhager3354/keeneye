package ru.dzhager3354.keeneye;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Student API",
				description = "Start project by dzhager3354",
				version = "1.0"
		)
)
public class KeeneyeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeeneyeApplication.class, args);
	}

}
