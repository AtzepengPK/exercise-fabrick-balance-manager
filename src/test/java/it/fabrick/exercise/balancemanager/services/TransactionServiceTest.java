package it.fabrick.exercise.balancemanager.transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionFabrick;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionListFabrick;
import it.fabrick.exercise.balancemanager.dao.transactions.TransactionEntity;
import it.fabrick.exercise.balancemanager.dao.transactions.TransactionsDao;
import it.fabrick.exercise.balancemanager.dto.transactions.DtoTransaction;
import it.fabrick.exercise.balancemanager.errors.exceptions.input.NotFoundException;
import it.fabrick.exercise.balancemanager.services.TransactionService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTest {
	@Autowired
	ObjectMapper mapper;
	@Value("classpath:mocks/transactionItem.json")
	Resource mockTransactionItemResource;
	@Autowired
	private TransactionService transactionService;
	@MockBean
	private FabrickClient fabrickClient;
	@MockBean
	private TransactionsDao transactionsDao;

	@Test
	public void testGetTransactions() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(Constants.FABRICK_DATE_FORMAT);
		Date fromAccountingDate = df.parse("2023-01-01");
		Date toAccountingDate = df.parse("2023-12-31");

		TransactionFabrick transactionFabrick = mapper.readValue(mockTransactionItemResource.getInputStream(), TransactionFabrick.class);
		TransactionListFabrick transactionListFabrick = new TransactionListFabrick(Collections.singletonList(transactionFabrick));

		FabrickResponse<TransactionListFabrick> fabrickResponse = new FabrickResponse<>();
		fabrickResponse.setPayload(transactionListFabrick);

		when(fabrickClient.getTransactions(anyString(), anyString(), anyString())).thenReturn(fabrickResponse);

		List<DtoTransaction> result = transactionService.getTransactions("account123", fromAccountingDate, toAccountingDate);

		verify(transactionsDao, times(1)).save(any(TransactionEntity.class));
		assertEquals(1, result.size());
		assertEquals(transactionFabrick.transactionId(), result.getFirst().getTransactionId());
	}

	@Test
	public void testGetTransactionById() {
		TransactionEntity entity = new TransactionEntity();
		entity.setTransactionId("12345");
		entity.setAmount(new java.math.BigDecimal(100));

		when(transactionsDao.findById(anyLong())).thenReturn(Optional.of(entity));

		DtoTransaction result = transactionService.getTransactionById("account123", 12345L);

		assertEquals("12345", result.getTransactionId());
		assertEquals(new java.math.BigDecimal(100), result.getAmount());
	}

	@Test
	public void testGetTransactionByIdNotFound() {
		when(transactionsDao.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> transactionService.getTransactionById("account123", 999L));
	}
}
