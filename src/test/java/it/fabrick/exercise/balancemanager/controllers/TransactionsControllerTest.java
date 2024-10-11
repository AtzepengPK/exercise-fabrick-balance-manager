package it.fabrick.exercise.balancemanager.controllers;

import it.fabrick.exercise.balancemanager.dto.transactions.DtoTransaction;
import it.fabrick.exercise.balancemanager.services.TransactionService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionService transactionService;

	@Test
	public void testGetTransactions() throws Exception {
		DtoTransaction transaction = new DtoTransaction();
		transaction.setTransactionId("12345");

		when(transactionService.getTransactions(anyString(), any(Date.class), any(Date.class)))
			.thenReturn(Collections.singletonList(transaction));

		SimpleDateFormat df = new SimpleDateFormat(Constants.FABRICK_DATE_FORMAT);
		String fromAccountingDate = df.format(new Date());
		String toAccountingDate = df.format(new Date());

		mockMvc.perform(get(Constants.Routes.VERSION1 + Constants.Routes.Transactions.ROOT, "account123")
				.param("fromAccountingDate", fromAccountingDate)
				.param("toAccountingDate", toAccountingDate)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.payload._embedded.transactions[0].transactionId").value("12345"));
	}

	@Test
	public void testGetTransactionById() throws Exception {
		DtoTransaction transaction = new DtoTransaction();
		transaction.setTransactionId("12345");

		when(transactionService.getTransactionById(anyString(), anyLong())).thenReturn(transaction);

		mockMvc.perform(get(Constants.Routes.VERSION1 + Constants.Routes.Transactions.ROOT + Constants.Routes.Transactions.GET, "account123", "12345")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.payload.transactionId").value("12345"));
	}
}
