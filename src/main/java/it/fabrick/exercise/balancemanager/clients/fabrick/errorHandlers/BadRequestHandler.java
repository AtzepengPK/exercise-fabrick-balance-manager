package it.fabrick.exercise.balancemanager.clients.fabrick.errorHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickErrorItem;
import it.fabrick.exercise.balancemanager.clients.fabrick.dto.FabrickResponse;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;
import it.fabrick.exercise.balancemanager.errors.exceptions.application.TechnicalException;
import it.fabrick.exercise.balancemanager.errors.exceptions.utils.BaseExceptionFactory;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class BadRequestHandler implements RestClient.ResponseSpec.ErrorHandler {
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final BaseExceptionFactory baseExceptionFactory = BaseExceptionFactory.getInstance();
	ExceptionCode exceptionCode;

	@Override
	public void handle(@Nonnull HttpRequest request, ClientHttpResponse response) throws IOException {

		exceptionCode = switch (response.getStatusCode()) {
			case HttpStatus.UNAUTHORIZED,
				 HttpStatus.METHOD_NOT_ALLOWED,
				 HttpStatus.UNSUPPORTED_MEDIA_TYPE -> ExceptionCode.DEVELOPER;
			case HttpStatus.TOO_MANY_REQUESTS -> ExceptionCode.UNAVAILABLE;
			default -> ExceptionCode.WRONG_INPUT;

		};

		Optional<FabrickResponse> body;
		try {
			body = Optional.of(objectMapper.readValue(response.getBody(), FabrickResponse.class));
		} catch (Exception e) {
			throw new TechnicalException("Error while parsing the response", e);
		}

		throw baseExceptionFactory.fromExceptionCode(exceptionCode,
			((FabrickErrorItem) body.orElseThrow(() -> new TechnicalException("Error while parsing the response"))
				.getErrors().getFirst()).description());
	}
}
