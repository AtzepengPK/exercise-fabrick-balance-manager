package it.fabrick.exercise.balancemanager.errors.exceptions.input;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;

public class ConflictException extends BaseRuntimeException {
	public static final String title = "Conflict";
	public static final ExceptionCode code = ExceptionCode.CONFLICT;

	public ConflictException(String message, Throwable cause, boolean enableSuppression,
							 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ConflictException(String message) {
		super(message);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ConflictException(Throwable cause) {
		super(cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}
}
