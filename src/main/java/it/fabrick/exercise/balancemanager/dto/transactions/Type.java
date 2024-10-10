package it.fabrick.exercise.balancemanager.dto.transactions;

import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TypeFabrick;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Type {
	@Column(name = "type_enumeration")
	private String enumeration;

	@Column(name = "type_value")
	private String value;

	public Type(TypeFabrick type) {
		this.enumeration = type.enumeration();
		this.value = type.value();
	}
}
