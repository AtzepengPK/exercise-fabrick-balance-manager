package it.fabrick.exercise.balancemanager.services;

import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionFabrick;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionListFabrick;
import it.fabrick.exercise.balancemanager.dao.transactions.TransactionsDao;
import it.fabrick.exercise.balancemanager.dto.transactions.DtoTransaction;
import it.fabrick.exercise.balancemanager.errors.exceptions.input.NotFoundException;
import it.fabrick.exercise.balancemanager.utils.Constants;
import it.fabrick.exercise.balancemanager.utils.mappers.TransactionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final @Qualifier(Constants.Clients.FABRICK) FabrickClient fabrickClient;
	private final TransactionsDao transactionsDao;

	public List<DtoTransaction> getTransactions(String accountId, Date fromAccountingDate, Date toAccountingDate) {
		DateFormat df = new SimpleDateFormat(Constants.FABRICK_DATE_FORMAT);
		FabrickResponse<TransactionListFabrick> response = fabrickClient.getTransactions(accountId, df.format(fromAccountingDate), df.format(toAccountingDate));

		List<TransactionFabrick> transactions = response.getPayload().list();

		List<DtoTransaction> result = new ArrayList<>();

		transactions.parallelStream()
			.forEach(t -> {
				transactionsDao.save(TransactionsMapper.INSTANCE.recordToEntity(t));
				result.add(TransactionsMapper.INSTANCE.recordToDto(t));
			});
		return result;
	}

	public DtoTransaction getTransactionById(String accountId, Long transactionId) {
		return TransactionsMapper.INSTANCE.entityToDto(
			transactionsDao.findById(transactionId).orElseThrow(
				() -> new NotFoundException("No transaction with id: %s".formatted(transactionId))
			));
	}
}
