package it.fabrick.exercise.balancemanager.clients.fabrick.dto;

public record FabrickErrorItem(
	String code,
	String description,
	String params) {
}
