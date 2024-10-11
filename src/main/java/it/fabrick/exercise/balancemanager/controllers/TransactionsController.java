package it.fabrick.exercise.balancemanager.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.fabrick.exercise.balancemanager.dto.DtoResponse;
import it.fabrick.exercise.balancemanager.dto.transactions.DtoTransaction;
import it.fabrick.exercise.balancemanager.services.TransactionService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constants.Routes.VERSION1, produces = MediaTypes.HAL_JSON_VALUE)
@Tag(name = "Transactions API", description = "Transactions API")
@ExposesResourceFor(DtoTransaction.class)
public class TransactionsController {
	private final TransactionService transactionService;

	@GetMapping(value = Constants.Routes.Transactions.ROOT)
	public DtoResponse<CollectionModel<DtoTransaction>> transactions(@PathVariable String accountId,
																	 @RequestParam @DateTimeFormat(pattern = Constants.FABRICK_DATE_FORMAT) Date fromAccountingDate,
																	 @RequestParam @DateTimeFormat(pattern = Constants.FABRICK_DATE_FORMAT) Date toAccountingDate) {
		List<DtoTransaction> transactionList = transactionService.getTransactions(accountId, fromAccountingDate, toAccountingDate);

		//HAL
		transactionList.forEach((t) -> {
			t.add(linkTo(methodOn(TransactionsController.class).transaction(accountId, Long.valueOf(t.getTransactionId()))).withSelfRel());
		});

		Link selfLink = linkTo(methodOn(TransactionsController.class)
			.transactions(accountId, fromAccountingDate, toAccountingDate)).withSelfRel();
		Link balanceLink = linkTo(methodOn(BalanceController.class).balance(accountId)).withRel("balance");

		CollectionModel<DtoTransaction> result = CollectionModel.of(transactionList, selfLink, balanceLink);
		return DtoResponse.ok(result);
	}

	@GetMapping(value = Constants.Routes.Transactions.ROOT + Constants.Routes.Transactions.GET)
	public DtoResponse<DtoTransaction> transaction(@PathVariable String accountId, @PathVariable Long transactionId) {
		DtoTransaction transaction = transactionService.getTransactionById(accountId, transactionId);

		//HAL
		Link selfLink = linkTo(methodOn(TransactionsController.class).transaction(accountId, transactionId)).withSelfRel();
		transaction.add(selfLink);

		return DtoResponse.ok(transaction);
	}
}
