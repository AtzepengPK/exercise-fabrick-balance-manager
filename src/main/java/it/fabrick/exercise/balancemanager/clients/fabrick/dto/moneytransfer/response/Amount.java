package it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.utils.Constants;

import java.math.BigDecimal;
import java.util.Date;

public record Amount(
	float exchangeRate,
	BigDecimal creditorAmount,
	BigDecimal debtorAmount,
	String debtorCurrency,
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date creditorCurrencyDate,
	String creditorCurrency
) {
}
