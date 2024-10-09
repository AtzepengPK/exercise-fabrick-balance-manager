package it.fabrick.exercise.balancemanager.errors;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import it.fabrick.exercise.balancemanager.dto.DtoError;
import it.fabrick.exercise.balancemanager.dto.DtoResponse;
import it.fabrick.exercise.balancemanager.errors.exceptions.BaseException;
import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Priority(0)
public class GlobalExceptionHandler {

	@ExceptionHandler(CallNotPermittedException.class)
	protected ResponseEntity<Object> handleCallNotPermittedException(CallNotPermittedException ex) {
		logException(ex);
		DtoResponse<Void> response = DtoResponse.ko(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}

	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<Object> handleBaseException(BaseException ex) {
		logException(ex);
		DtoResponse<Void> response = DtoResponse.ko(ex);
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}

	@ExceptionHandler(BaseRuntimeException.class)
	protected ResponseEntity<Object> handleBaseRuntimeException(BaseRuntimeException ex) {
		logException(ex);
		DtoResponse<Void> response = DtoResponse.ko(ex);
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex) {
		logException(ex);
		DtoResponse<Void> response = DtoResponse.ko(DtoError.ko(ex.getMessage()));
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}

	private void logException(Exception ex) {
		String PRE_ERROR_MSG = "An unexpected error occurred : ";
		if (StringUtils.hasText(ex.getMessage())) {
			log.error("{}{}", PRE_ERROR_MSG, ex.getMessage(), ex);
		} else {
			log.error(PRE_ERROR_MSG, ex);
		}
	}
}
