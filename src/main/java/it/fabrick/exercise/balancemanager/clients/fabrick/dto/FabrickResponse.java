package it.fabrick.exercise.balancemanager.clients.fabrick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabrickResponse<T> {
	FabrickStatus status;
	List<FabrickErrorItem> errors;
	T payload;
}
