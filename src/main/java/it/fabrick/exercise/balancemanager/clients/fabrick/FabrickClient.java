package it.fabrick.exercise.balancemanager.clients.fabrick;

import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClientConfig.Routes;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance.Balance;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionListFabrick;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@Validated
@HttpExchange(accept = {"application/json"}, contentType = "application/json")
public interface FabrickClient {
	@GetExchange(Routes.Accounts.ROOT + Routes.Accounts.ACCOUNT + Routes.Accounts.BALANCE)
	FabrickResponse<Balance> getBalance(@PathVariable String accountId);

	@GetExchange(Routes.Accounts.ROOT + Routes.Accounts.ACCOUNT + Routes.Accounts.TRANSACTIONS)
	FabrickResponse<TransactionListFabrick> getTransactions(@PathVariable String accountId, @RequestParam String fromAccountingDate,
															@RequestParam String toAccountingDate);

	@PostExchange(Routes.Accounts.ROOT + Routes.Accounts.ACCOUNT + Routes.Accounts.MONEY_TRANSFERS)
	String moneyTransfers(@PathVariable String accountId,
						  @RequestBody @Valid String requestBody);
}
