package it.fabrick.exercise.balancemanager.errors.exceptions.application;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;

public class UnauthorizedException extends BaseRuntimeException {
	public static final String title = "Unauthorized exception";
	public static final ExceptionCode code = ExceptionCode.UNAUTHORIZED;

	public UnauthorizedException(String message, Throwable cause, boolean enableSuppression,
								 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public UnauthorizedException(String message) {
		super(message);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}
}
