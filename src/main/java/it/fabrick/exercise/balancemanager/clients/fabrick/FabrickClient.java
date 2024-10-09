package it.fabrick.exercise.balancemanager.clients.fabrick;

import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClientConfig.Routes;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance.Balance;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Date;

@Validated
@HttpExchange(accept = {"application/json"}, contentType = "application/json")
public interface FabrickClient {
	@GetExchange(Routes.Accounts.ROOT + Routes.Accounts.ACCOUNT + Routes.Accounts.BALANCE)
	FabrickResponse<Balance> getBalance(@PathVariable String accountId);

	@GetExchange(Routes.Accounts.ROOT + Routes.Accounts.ACCOUNT + Routes.Accounts.TRANSACTIONS)
	String getTransactions(@PathVariable String accountId, @RequestParam Date fromAccountingDate,
						   @RequestParam Date toAccountingDate);

	@PostExchange(Routes.Accounts.ROOT + Routes.Accounts.ACCOUNT + Routes.Accounts.MONEY_TRANSFERS)
	String moneyTransfers(@PathVariable String accountId,
						  @RequestBody @Valid String requestBody);
}
