package it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer;

public record Creditor(
	Address address,
	String name,
	Account account
) {
	public Creditor(String creditorName) {
		this(null, creditorName, null);
	}
}
