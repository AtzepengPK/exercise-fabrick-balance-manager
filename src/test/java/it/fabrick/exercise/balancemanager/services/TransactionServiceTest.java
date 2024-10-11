//package it.fabrick.exercise.balancemanager.transactions;
//
//import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
//import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
//import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionFabrick;
//import it.fabrick.exercise.balancemanager.clients.fabrick.dto.transaction.TransactionListFabrick;
//import it.fabrick.exercise.balancemanager.dao.transactions.TransactionEntity;
//import it.fabrick.exercise.balancemanager.dao.transactions.TransactionsDao;
//import it.fabrick.exercise.balancemanager.dto.transactions.DtoTransaction;
//import it.fabrick.exercise.balancemanager.services.TransactionService;
//import it.fabrick.exercise.balancemanager.utils.Constants;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.text.SimpleDateFormat;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class TransactionServiceTest {
//
//	@Autowired
//	private TransactionService transactionService;
//
//	@MockBean
//	private FabrickClient fabrickClient;
//
//	@MockBean
//	private TransactionsDao transactionsDao;
//
//	@Test
//	public void testGetTransactions() throws Exception {
//		// Prepara la data di esempio
//		SimpleDateFormat df = new SimpleDateFormat(Constants.FABRICK_DATE_FORMAT);
//		Date fromAccountingDate = df.parse("2023-01-01");
//		Date toAccountingDate = df.parse("2023-12-31");
//
//		// Mock del response dal client Fabrick
//		TransactionFabrick transactionFabrick = new TransactionFabrick();
//		transactionFabrick.setTransactionId("12345");
//		transactionFabrick.setAmount(100.00);
//
//		TransactionListFabrick transactionListFabrick = new TransactionListFabrick();
//		transactionListFabrick.setList(Collections.singletonList(transactionFabrick));
//
//		FabrickResponse<TransactionListFabrick> fabrickResponse = new FabrickResponse<>();
//		fabrickResponse.setPayload(transactionListFabrick);
//
//		// Configura il comportamento del mock
//		when(fabrickClient.getTransactions(anyString(), anyString(), anyString())).thenReturn(fabrickResponse);
//
//		// Esegui il metodo del servizio
//		List<DtoTransaction> result = transactionService.getTransactions("account123", fromAccountingDate, toAccountingDate);
//
//		// Verifica che il DAO salvi i dati
//		verify(transactionsDao, times(1)).save(any(TransactionEntity.class));
//
//		// Verifica la dimensione del risultato
//		assertEquals(1, result.size());
//		assertEquals("12345", result.get(0).getTransactionId());
//	}
//
//	@Test
//	public void testGetTransactionById() {
//		// Mock del response dal DAO
//		TransactionEntity entity = new TransactionEntity();
//		entity.setTransactionId("12345");
//		entity.setAmount(new java.math.BigDecimal(100));
//
//		when(transactionsDao.findById(anyLong())).thenReturn(Optional.of(entity));
//
//		// Esegui il metodo del servizio
//		DtoTransaction result = transactionService.getTransactionById("account123", 12345L);
//
//		// Verifica il risultato
//		assertEquals("12345", result.getTransactionId());
//		assertEquals(new java.math.BigDecimal(100), result.getAmount());
//	}
//
//	@Test
//	public void testGetTransactionByIdNotFound() {
//		// Mock del response vuoto dal DAO
//		when(transactionsDao.findById(anyLong())).thenReturn(Optional.empty());
//
//		// Verifica che venga lanciata l'eccezione NotFoundException
//		assertThrows(NotFoundException.class, () -> transactionService.getTransactionById("account123", 999L));
//	}
//}
