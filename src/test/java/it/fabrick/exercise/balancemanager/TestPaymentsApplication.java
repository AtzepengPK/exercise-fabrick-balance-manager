package it.fabrick.exercise.balancemanager;

import org.springframework.boot.SpringApplication;

public class TestPaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.from(PaymentsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
