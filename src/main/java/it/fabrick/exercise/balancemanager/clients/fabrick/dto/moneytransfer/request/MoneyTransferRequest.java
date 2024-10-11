package it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.Creditor;
import it.fabrick.exercise.balancemanager.utils.Constants;

import java.math.BigDecimal;
import java.util.Date;

public record MoneyTransferRequest(
	boolean isUrgent,
	BigDecimal amount,
	TaxRelief taxRelief,
	String feeAccountId,
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date executionDate,
	boolean isInstant,
	String description,
	Creditor creditor,
	String currency,
	String feeType,
	String uri) {


	public MoneyTransferRequest(String creditorName, String description, String currency, BigDecimal amount) {
		this(false,
			amount,
			null,
			null,
			null,
			false,
			description,
			new Creditor(creditorName),
			currency,
			null,
			null);

	}

}

