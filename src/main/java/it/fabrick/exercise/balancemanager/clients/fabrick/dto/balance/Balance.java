package it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.utils.Constants;

import java.util.Date;

public record Balance(
	Double availableBalance,
	Double balance,
	String currency,
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date date
) {
}
