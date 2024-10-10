package it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public record TransactionFabrick(
	String transactionId,
	String operationId,
	Date accountingDate,
	Date valueDate,
	BigDecimal amount,
	Currency currency,
	String description,
	TypeFabrick type
) {
}
