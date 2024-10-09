package it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance;

public record Balance(
	Double availableBalance,
	Double balance,
	String currency,
	String date
) {
}
