package it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response;

import java.math.BigDecimal;

public record FeesItem(
	BigDecimal amount,
	String feeCode,
	String description,
	String currency
) {
}
