package it.fabrick.exercise.balancemanager.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.balance.Balance;
import it.fabrick.exercise.balancemanager.dto.DtoResponse;
import it.fabrick.exercise.balancemanager.services.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
@Tag(name = "Balance API", description = "Balance API")
public class BalanceController {
	private final BalanceService balanceService;

	@GetMapping("/{accountId}")
	public DtoResponse<Balance> balance(@PathVariable String accountId) {
		return DtoResponse.ok(balanceService.getBalance(accountId));
	}
}
