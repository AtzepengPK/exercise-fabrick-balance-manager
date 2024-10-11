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

	private final @Qualifier(Constants.Clients.FABRICK) FabrickClient fabrickClient;

	public Balance getBalance(String accountId) {
		FabrickResponse<Balance> balance = fabrickClient.getBalance(accountId);
		return balance.getPayload();
	}
}
