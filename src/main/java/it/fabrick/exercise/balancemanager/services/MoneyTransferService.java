package it.fabrick.exercise.balancemanager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.request.MoneyTransferRequest;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.moneytransfer.response.MoneyTransferResponse;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class MoneyTransferService {
	private final @Qualifier(Constants.Clients.FABRICK) FabrickClient fabrickClient;
	private final ObjectMapper objectMapper;

	@Value("classpath:mocks/moneyTransferResponse.json")
	Resource resourceFile;

	@Cacheable("moneyTransfer")
	@CircuitBreaker(name = "moneyTransfer")
	@Retry(name = "moneyTransfer")
	public MoneyTransferResponse moneyTransfer(String accountId, boolean mock) throws IOException {
		if (mock) {
			return objectMapper.readValue(resourceFile.getInputStream(), MoneyTransferResponse.class);
		}

		MoneyTransferRequest request = new MoneyTransferRequest("test", "test", "EUR", BigDecimal.TEN);
		return fabrickClient.moneyTransfers(accountId, request).getPayload();
	}
}
