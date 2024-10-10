package it.fabrick.exercise.balancemanager.clients;

import io.micrometer.observation.ObservationRegistry;
import it.fabrick.exercise.balancemanager.clients.fabrick.errorHandlers.BadRequestHandler;
import it.fabrick.exercise.balancemanager.clients.fabrick.errorHandlers.InternalServerErrorHandler;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
	private final ObservationRegistry observationRegistry;
	@Value("${config.clients.connection-timeout-seconds:30}")
	private int connectionTimeout;

	@Bean(Constants.Clients.Builders.PROJECT)
	public RestClient.Builder projectClientBuilder() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(Duration.ofSeconds(this.connectionTimeout));

		return RestClient.builder()
			.requestFactory(requestFactory)
			.observationRegistry(observationRegistry)
			.defaultStatusHandler(HttpStatusCode::is4xxClientError, new BadRequestHandler())
			.defaultStatusHandler(HttpStatusCode::is5xxServerError, new InternalServerErrorHandler());
	}

}
