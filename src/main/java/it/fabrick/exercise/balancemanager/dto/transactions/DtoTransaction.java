package it.fabrick.exercise.balancemanager.dto.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "transactions")
public class DtoTransaction extends RepresentationModel<DtoTransaction> {
	private String transactionId;
	private String operationId;
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	private Date accountingDate;
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	private Date valueDate;
	private BigDecimal amount;
	private Currency currency;
	private String description;
	private Type type;
}
