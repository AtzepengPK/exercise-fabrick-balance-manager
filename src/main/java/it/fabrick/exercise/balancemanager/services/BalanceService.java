package it.fabrick.exercise.balancemanager.services;

import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance.Balance;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

	//	private final CircuitBreakerFactory circuitBreakerFactory;
	private final @Qualifier(Constants.Clients.FABRICK) FabrickClient fabrickClient;

	public Balance getBalance(String accountId) {
		FabrickResponse<Balance> balance = fabrickClient.getBalance(accountId);

		return balance.getPayload();


//		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
//		return circuitBreaker.run(() -> fabrickClient.getBalance("1234567890"), (t) -> {
//			throw new ApplicationException(t);
//		});
	}
}
