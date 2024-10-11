package it.fabrick.exercise.balancemanager.clients.fabrick.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FabrickResponse<T> {
	FabrickStatus status;
	List<FabrickErrorItem> errors;
	T payload;
}
