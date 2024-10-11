package it.fabrick.exercise.balancemanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import it.fabrick.exercise.balancemanager.services.MoneyTransferService;
import it.fabrick.exercise.balancemanager.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MoneyTransferControllerTest {

	@Value("classpath:mocks/moneyTransferResponse.json")
	Resource mockResponseResource;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private MoneyTransferService moneyTransferService;

	@Test
	public void testExecuteTransfer() throws Exception {

		MoneyTransferResponse mockResponse = mapper.readValue(mockResponseResource.getInputStream(), MoneyTransferResponse.class);

		when(moneyTransferService.moneyTransfer(anyString(), anyBoolean())).thenReturn(mockResponse);

		mockMvc.perform(post(Constants.Routes.VERSION1 + Constants.Routes.MoneyTransfer.ROOT, "account123")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.payload.cro").value(mockResponse.cro()));
		//TODO: Add rest of validation
	}
}
