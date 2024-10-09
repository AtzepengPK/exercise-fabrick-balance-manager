//package it.fabrick.exercise.balancemanager;
//
//import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClient;
//import it.fabrick.exercise.balancemanager.clients.fabrick.FabrickClientConfig;
//import it.fabrick.exercise.balancemanager.utils.Constants;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.stream.IntStream;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
////@Import(TestBeans.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class TestCircuitBreaker {
//	@MockBean(name = Constants.Clients.FABRICK)
//	private FabrickClient fabrickClient;
//	@Autowired
//	private MockRestServiceServer mockFabrickServer;
//
//
//	@Autowired
//	private MockMvc mockMvc;
//
//
//	@Test
//	public void testCircuitBreaker() {
//
//		when(fabrickClient.getBalance(anyString()))
//			.then(throw new RuntimeException());
//
//		IntStream.rangeClosed(1, 5)
//			.forEach(i -> {
//				try {
//					this.mockMvc.perform(MockMvcRequestBuilders.get("/balance"))
//						.andExpect(MockMvcResultMatchers.status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
//				} catch (Exception e) {
//					throw new RuntimeException(e);
//				}
//			});
//
//		IntStream.rangeClosed(1, 5)
//			.forEach(i -> {
//				try {
//					this.mockMvc.perform(MockMvcRequestBuilders.get("/balance"))
//						.andExpect(MockMvcResultMatchers.status().is(HttpStatus.SERVICE_UNAVAILABLE.value()));
//				} catch (Exception e) {
//					throw new RuntimeException(e);
//				}
//			});
//
//		wiremock.verify(5, getRequestedFor(urlEqualTo(FabrickClientConfig.Routes.Accounts.ROOT +
//			FabrickClientConfig.Routes.Accounts.ACCOUNT +
//			FabrickClientConfig.Routes.Accounts.BALANCE)));
//	}
//
//
//}
