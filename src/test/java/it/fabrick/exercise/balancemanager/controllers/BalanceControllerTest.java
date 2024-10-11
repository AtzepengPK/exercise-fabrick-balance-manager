package it.fabrick.exercise.balancemanager.controllers;


import it.fabrick.exercise.balancemanager.dto.balance.DtoBalance;
import it.fabrick.exercise.balancemanager.services.BalanceService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BalanceService balanceService;

	@Test
	public void testGetBalance() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.FABRICK_DATE_FORMAT);
		DateTimeFormatter.ofPattern(Constants.FABRICK_DATE_FORMAT);

		DtoBalance balance = DtoBalance.builder()
			.availableBalance(123.123)
			.balance(234.234)
			.currency("EUR")
			.date(new Date()).build();

		when(balanceService.getBalance(anyString())).thenReturn(balance);

		mockMvc.perform(get(Constants.Routes.VERSION1 + Constants.Routes.Balance.ROOT, "account123")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.payload.availableBalance").value(123.123))
			.andExpect(jsonPath("$.payload.balance").value(234.234))
			.andExpect(jsonPath("$.payload.currency").value("EUR"))
			.andExpect(jsonPath("$.payload.date").value(LocalDate.now().format(formatter)));
		;
	}
}
