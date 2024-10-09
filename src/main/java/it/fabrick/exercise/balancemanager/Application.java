package it.fabrick.exercise.balancemanager;

import it.fabrick.exercise.balancemanager.errors.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "it.fabrick")
@Import(GlobalExceptionHandler.class)
public class Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
