package it.fabrick.exercise.balancemanager.errors.exceptions.input;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;

public class NotFoundException extends BaseRuntimeException {
	public static final String title = "Not Found";
	public static final ExceptionCode code = ExceptionCode.NOT_FOUND;

	public NotFoundException(String message, Throwable cause, boolean enableSuppression,
							 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public NotFoundException(String message) {
		super(message);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}
}
