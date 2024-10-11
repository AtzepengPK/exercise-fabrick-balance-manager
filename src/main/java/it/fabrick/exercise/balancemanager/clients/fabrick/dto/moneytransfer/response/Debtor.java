package it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response;

import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.Account;

public record Debtor(
	String name,
	Account account
) {
}
