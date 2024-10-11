package it.fabrick.exercise.balancemanager.errors;

import io.github.resilience4j.retry.event.RetryOnErrorEvent;
import io.github.resilience4j.retry.event.RetryOnRetryEvent;
import io.github.resilience4j.retry.event.RetryOnSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomRetryEventListener {

	@EventListener
	public void onRetryEvent(RetryOnRetryEvent event) {
		log.info("Retry attempt #{} for {}. Waiting to retry...",
			event.getNumberOfRetryAttempts(), event.getName());
	}

	@EventListener
	public void onSuccessEvent(RetryOnSuccessEvent event) {
		log.info("Retry succeeded after {} attempts for {}.",
			event.getNumberOfRetryAttempts(), event.getName());
	}

	@EventListener
	public void onErrorEvent(RetryOnErrorEvent event) {
		log.error("Retry failed after {} attempts for {}. Error: {}",
			event.getNumberOfRetryAttempts(), event.getName(), event.getLastThrowable().getMessage());
	}
}
