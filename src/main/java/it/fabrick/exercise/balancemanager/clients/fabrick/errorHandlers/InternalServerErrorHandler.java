package it.fabrick.exercise.balancemanager.clients.fabrick.errorHandlers;

import it.fabrick.exercise.balancemanager.errors.exceptions.application.ServiceUnavailableException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

public class InternalServerErrorHandler implements RestClient.ResponseSpec.ErrorHandler {
	@Override
	public void handle(HttpRequest request, ClientHttpResponse response) {
		throw new ServiceUnavailableException("Service Unavailable");
	}
}
