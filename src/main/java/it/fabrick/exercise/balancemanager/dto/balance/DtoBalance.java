package it.fabrick.exercise.balancemanager.dto.balance;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBalance extends RepresentationModel<DtoBalance> {
	Double availableBalance;
	Double balance;
	String currency;
	String date;
}
