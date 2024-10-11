package it.fabrick.exercise.balancemanager.dao.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.dto.transactions.Type;
import it.fabrick.exercise.balancemanager.utils.Constants;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class TransactionEntity {
	@Id
	private String transactionId;
	@Column(nullable = false)
	private String operationId;
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date accountingDate;
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date valueDate;
	private BigDecimal amount;
	private Currency currency;
	private String description;
	@Embedded
	private Type type;
}
