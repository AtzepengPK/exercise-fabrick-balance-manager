package it.fabrick.exercise.balancemanager.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.fabrick.exercise.balancemanager.dto.DtoResponse;
import it.fabrick.exercise.balancemanager.dto.balance.DtoBalance;
import it.fabrick.exercise.balancemanager.services.BalanceService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constants.Routes.VERSION1, produces = MediaTypes.HAL_JSON_VALUE)
@Tag(name = "Balance API", description = "Balance API")
public class BalanceController {
	private final BalanceService balanceService;

	@GetMapping(Constants.Routes.Balance.ROOT)
	public DtoResponse<DtoBalance> balance(@PathVariable String accountId) {
		DtoBalance balance = balanceService.getBalance(accountId);

		//HAL
		Link selfLink = linkTo(methodOn(BalanceController.class).balance(accountId)).withSelfRel();
		Link transactionsLink = linkTo(methodOn(TransactionsController.class).transactions(accountId,
			Date.from(Instant.now().minus(7, java.time.temporal.ChronoUnit.DAYS)), Date.from(Instant.now())
		)).withRel("transactions");

		balance.add(selfLink, transactionsLink);
		return DtoResponse.ok(balance);
	}
}
