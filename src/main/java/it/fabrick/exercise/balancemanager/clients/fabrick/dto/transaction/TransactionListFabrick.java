package it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction;

import java.util.Collections;
import java.util.List;

public record TransactionListFabrick(List<TransactionFabrick> list) {
	public TransactionListFabrick {
		list = Collections.unmodifiableList(list);
	}
}
