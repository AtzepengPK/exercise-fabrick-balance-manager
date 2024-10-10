package it.fabrick.exercise.balancemanager.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.fabrick.exercise.balancemanager.dto.DtoResponse;
import it.fabrick.exercise.balancemanager.dto.transactions.DtoTransaction;
import it.fabrick.exercise.balancemanager.services.TransactionService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
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
@RequestMapping("/api/v1/transactions")
@Tag(name = "Balance API", description = "Balance API")
public class TransactionsController {
	private final TransactionService transactionService;

	@GetMapping(value = "/{accountId}", produces = {"application/hal+json"})
	public DtoResponse<CollectionModel<DtoTransaction>> transactions(@PathVariable String accountId,
																	 @RequestParam Date fromAccountingDate,
																	 @RequestParam Date toAccountingDate) {
		List<DtoTransaction> transactionList = transactionService.getTransactions(accountId, fromAccountingDate, toAccountingDate);

		transactionList.parallelStream()
			.forEach(t -> {
				Link selfLink = Link.of("%s%s%s%s".formatted("/", Constants.DaoRoutes.TRANSACTIONS, "/", t.getTransactionId()));
				t.add(selfLink);
			});

		Link link = linkTo(methodOn(TransactionsController.class)
			.transactions(accountId, fromAccountingDate, toAccountingDate)).withSelfRel();

		CollectionModel<DtoTransaction> result = CollectionModel.of(transactionList, link);

		return DtoResponse.ok(result);

	}
}
