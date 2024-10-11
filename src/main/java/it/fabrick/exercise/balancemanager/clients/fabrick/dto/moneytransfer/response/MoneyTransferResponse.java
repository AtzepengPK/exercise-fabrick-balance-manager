package it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.Creditor;
import it.fabrick.exercise.balancemanager.utils.Constants;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public record MoneyTransferResponse(
	String cro,
	boolean isUrgent,
	Amount amount,
	List<FeesItem> fees,
	String feeAccountId,
	String moneyTransferId,
	boolean isInstant,
	String description,
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date debtorValueDate,
	String feeType,
	String uri,
	boolean hasTaxRelief,
	String trn,
	Debtor debtor,
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date creditorValueDate,
	Creditor creditor,
	@JsonFormat(pattern = Constants.FABRICK_DATETIME_FORMAT)
	OffsetDateTime createdDatetime,
	@JsonFormat(pattern = Constants.FABRICK_DATETIME_FORMAT)
	OffsetDateTime accountedDatetime,
	String status,
	String direction
) {
}
