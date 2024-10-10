package it.fabrick.exercise.balancemanager.dto.balance;

import it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance.Balance;
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

	public DtoBalance(Balance balance) {
		this.availableBalance = balance.availableBalance();
		this.balance = balance.balance();
		this.currency = balance.currency();
		this.date = balance.date();
	}
}
