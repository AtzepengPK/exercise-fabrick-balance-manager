package it.fabrick.exercise.balancemanager.dto;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseException;
import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;
import it.fabrick.exercise.balancemanager.errors.exceptions.application.ApplicationException;
import it.fabrick.exercise.balancemanager.errors.exceptions.utils.BaseExceptionFactory;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DtoError {
	private static final BaseExceptionFactory baseExceptionFactory = BaseExceptionFactory.getInstance();
	private String title;
	private ExceptionCode code;
	private String description;

	@Valid
	public static DtoError fromBaseException(@Nonnull BaseException exception) {
		return DtoError.builder()
			.code(exception.getExceptionCode())
			.title(exception.getTitle())
			.description(exception.getMessage())
			.build();
	}

	@Valid
	public static DtoError fromBaseRuntimeException(@Nonnull BaseRuntimeException exception) {
		return DtoError.builder()
			.code(exception.getExceptionCode())
			.title(exception.getTitle())
			.description(exception.getMessage())
			.build();
	}

	public static DtoError ko() {
		return fromBaseRuntimeException(new ApplicationException("Generic error"));
	}

	public static DtoError ko(String message) {
		return fromBaseRuntimeException(new ApplicationException(message));
	}

	public static DtoError fromExceptionCode(@Nonnull ExceptionCode exceptionCode, String message) {
		return fromBaseRuntimeException(baseExceptionFactory.fromExceptionCode(exceptionCode, message));
	}
}
