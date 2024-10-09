package it.fabrick.exercise.balancemanager.clients.fabrick;

import it.fabrick.exercise.balancemanager.clients.fabrick.errorHandlers.BadRequestHandler;
import it.fabrick.exercise.balancemanager.clients.fabrick.errorHandlers.InternalServerErrorHandler;
import it.fabrick.exercise.balancemanager.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class FabrickClientConfig {

	@Bean(Constants.Clients.FABRICK)
	FabrickClient fabrickClient() {
		return HttpServiceProxyFactory.builderFor(
				RestClientAdapter.create(RestClient.builder()
					.baseUrl("https://sandbox.platfr.io")
					.defaultHeader("Auth-Schema", "S2S")
					.defaultHeader("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
					.defaultStatusHandler(HttpStatusCode::is4xxClientError, new BadRequestHandler())
					.defaultStatusHandler(HttpStatusCode::is5xxServerError, new InternalServerErrorHandler())
					.build())).build()
			.createClient(FabrickClient.class);
	}

	public static final class Routes {
		public static final String ROOT = "/api/gbs/banking";
		public static final String VERSION = "/v4.0";

		public static final class Accounts {
			public static final String ROOT = Routes.ROOT + Routes.VERSION + "/accounts";

			public static final String ACCOUNT = "/{accountId}";
			public static final String BALANCE = "/balance";
			public static final String TRANSACTIONS = "/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
			public static final String MONEY_TRANSFERS = "/payments/money-transfers";
		}
	}
}
