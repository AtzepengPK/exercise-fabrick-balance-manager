package it.fabrick.exercise.balancemanager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class MoneyTransferServiceTest {
	@Value("classpath:mocks/moneyTransferResponse.json")
	private Resource mockResponseResource;
	@Value("${resilience4j.retry.instances.moneyTransfer.maxAttempts}")
	private int numberOfRetries;

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private MoneyTransferService moneyTransferService;
	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;
	@MockBean
	private FabrickClient fabrickClient;


	//Todo: some issue with mockito doesn't reset the fabrickClient, failing the second tests that uses it
//	@Test
//	public void testCircuitBreakerClosed() throws IOException {
//		MoneyTransferResponse mockResponse = mapper.readValue(mockResponseResource.getInputStream(), MoneyTransferResponse.class);
//		circuitBreakerRegistry.circuitBreaker("moneyTransfer").transitionToClosedState();
//
//		when(fabrickClient.moneyTransfers(anyString(), any(MoneyTransferRequest.class))).thenReturn(
//			FabrickResponse.<MoneyTransferResponse>builder()
//				.payload(mockResponse)
//				.status(FabrickStatus.OK)
//				.build()
//		);
//
//		MoneyTransferResponse response = moneyTransferService.moneyTransfer("123", false);
//
//		Assertions.assertEquals(mockResponse.cro(), response.cro()); //TODO: Add rest of validation
//		verify(fabrickClient, times(1)).moneyTransfers(eq("123"), any(MoneyTransferRequest.class));
//	}

	@Test
	public void testCircuitBreakerOpen() {
		circuitBreakerRegistry.circuitBreaker("moneyTransfer").transitionToOpenState();
		when(fabrickClient.moneyTransfers(anyString(), any(MoneyTransferRequest.class))).thenThrow(new RuntimeException("Service is down"));

		Assertions.assertThrows(CallNotPermittedException.class, () -> moneyTransferService.moneyTransfer("123", false));
		verifyNoInteractions(fabrickClient);
	}

	@Test
	public void testRetry() throws IOException {
		when(fabrickClient.moneyTransfers(anyString(), any(MoneyTransferRequest.class)))
			.thenThrow(new RuntimeException("First Failure"))
			.thenThrow(new RuntimeException("Second Failure"))
			.thenReturn(FabrickResponse.<MoneyTransferResponse>builder().build());

		moneyTransferService.moneyTransfer("123", false);
		verify(fabrickClient, times(numberOfRetries)).moneyTransfers(anyString(), any(MoneyTransferRequest.class));
	}


}
