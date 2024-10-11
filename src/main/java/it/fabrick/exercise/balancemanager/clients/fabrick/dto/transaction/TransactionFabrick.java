package it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.utils.Constants;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public record TransactionFabrick(
	String transactionId,
	String operationId,
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date accountingDate,
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date valueDate,
	BigDecimal amount,
	Currency currency,
	String description,
	TypeFabrick type
) {
}
