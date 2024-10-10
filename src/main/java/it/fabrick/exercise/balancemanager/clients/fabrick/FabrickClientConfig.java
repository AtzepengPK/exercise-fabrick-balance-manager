package it.fabrick.exercise.balancemanager.clients.fabrick;

import it.fabrick.exercise.balancemanager.properties.FabrickProperties;
import it.fabrick.exercise.balancemanager.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
@RequiredArgsConstructor
public class FabrickClientConfig {
	private final FabrickProperties fabrickProperties;

	@Bean(Constants.Clients.FABRICK)
	FabrickClient fabrickClient(@Qualifier(Constants.Clients.Builders.PROJECT) RestClient.Builder projectClientBuilder) {
		return HttpServiceProxyFactory.builderFor(
				RestClientAdapter.create(projectClientBuilder
					.baseUrl(fabrickProperties.getUrl())
					.defaultHeader("Auth-Schema", fabrickProperties.getAuthSchema())
					.defaultHeader("Api-Key", fabrickProperties.getApiKey())
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
			public static final String TRANSACTIONS = "/transactions";
			public static final String MONEY_TRANSFERS = "/payments/money-transfers";
		}
	}
}
