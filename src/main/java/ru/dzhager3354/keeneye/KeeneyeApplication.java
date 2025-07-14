package ru.dzhager3354.keeneye;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dzhager3354.keeneye.service.InitService;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Student API",
				description = "Start project by dzhager3354",
				version = "1.0"
		)
)
public class KeeneyeApplication {
	private static InitService initializerService;

	@Autowired
	public void setInitialLoader(InitService initializerService) {
		KeeneyeApplication.initializerService = initializerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(KeeneyeApplication.class, args);
		initializerService.init();
	}

}
