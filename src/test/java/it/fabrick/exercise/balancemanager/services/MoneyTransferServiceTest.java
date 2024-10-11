package it.fabrick.exercise.balancemanager.services;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response.MoneyTransferResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MoneyTransferServiceTest {

	@Autowired
	private MoneyTransferService moneyTransferService;
	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;

	@MockBean
	private FabrickClient fabrickClient;

	@Value("${resilience4j.retry.instances.moneyTransfer.maxAttempts}")
	private int numberOfRetries;

//	@Test
//	public void testCircuitBreaker() throws IOException {
//		CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("moneyTransfer");
//
//		Assertions.assertEquals(CircuitBreaker.State.CLOSED, circuitBreaker.getState());
//
//		when(fabrickClient.moneyTransfers(anyString(), any(MoneyTransferRequest.class)))
//			.thenThrow(new RuntimeException("Service Unavailable"));
//
//		int i = 0;
//		while (circuitBreaker.getState() != CircuitBreaker.State.OPEN) {
//		}
//
//		Assertions.assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
//	}

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
