package it.fabrick.exercise.balancemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@EnableCaching
@EnableJpaRepositories
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@SpringBootApplication(scanBasePackages = "it.fabrick")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
