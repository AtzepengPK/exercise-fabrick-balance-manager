package it.fabrick.exercise.balancemanager.dto.balance;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBalance extends RepresentationModel<DtoBalance> {
	Double availableBalance;
	Double balance;
	String currency;
	@JsonFormat(pattern = Constants.FABRICK_DATE_FORMAT)
	Date date;
}
