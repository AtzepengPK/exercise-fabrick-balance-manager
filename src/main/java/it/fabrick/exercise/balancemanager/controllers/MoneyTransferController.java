package it.fabrick.exercise.balancemanager.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import it.fabrick.exercise.balancemanager.dto.DtoResponse;
import it.fabrick.exercise.balancemanager.services.MoneyTransferService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.Routes.VERSION1)
@Tag(name = "Money Transfer API", description = "Money Transfer API")
public class MoneyTransferController {

	private final MoneyTransferService moneyTransferService;

	@PostMapping(Constants.Routes.MoneyTransfer.ROOT)
	public DtoResponse<MoneyTransferResponse> moneyTransfer(@PathVariable String accountId, @RequestParam(required = false, defaultValue = "false") boolean mock) throws IOException {
		return DtoResponse.ok(moneyTransferService.moneyTransfer(accountId, mock));
	}
}
