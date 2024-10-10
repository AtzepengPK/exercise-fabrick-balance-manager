package it.fabrick.exercise.balancemanager;

import it.fabrick.exercise.balancemanager.errors.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "it.fabrick")
@Import(GlobalExceptionHandler.class)
@EnableJpaRepositories()
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
