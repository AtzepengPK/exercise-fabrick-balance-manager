package it.fabrick.exercise.balancemanager.services;

import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance.Balance;
import it.fabrick.exercise.balancemanager.dto.balance.DtoBalance;
import it.fabrick.exercise.balancemanager.utils.Constants;
import it.fabrick.exercise.balancemanager.utils.mappers.BalanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

	private final @Qualifier(Constants.Clients.FABRICK) FabrickClient fabrickClient;

	public DtoBalance getBalance(String accountId) {
		FabrickResponse<Balance> balance = fabrickClient.getBalance(accountId);
		return BalanceMapper.INSTANCE.recordToDto(balance.getPayload());
	}
}
