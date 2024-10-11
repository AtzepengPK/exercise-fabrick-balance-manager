package it.fabrick.exercise.balancemanager.utils;

public class Constants {
	public static final String FABRICK_DATE_FORMAT = "yyyy-MM-dd";
	public static final String FABRICK_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";


	public static class Routes {
		public static final String VERSION1 = "/v1";

		public static class Balance {
			public static final String ROOT = "/{accountId}/balance";
		}

		public static class Transactions {
			public static final String ROOT = "/{accountId}/transactions";
			public static final String GET = "/{transactionId}";
		}

		public static class MoneyTransfer {
			public static final String ROOT = "/{accountId}/money-transfer";
		}

		public static class DaoRoutes {
			public static final String TRANSACTIONS = "my-transactions";
		}
	}


	public static class Clients {
		public static final String FABRICK = "fabrick_client";

		public static class Builders {
			public static final String PROJECT = "project_client_builder";
		}
	}
}
