package it.fabrick.exercise.balancemanager.errors.exceptions.application;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;

public class ServiceUnavailableException extends BaseRuntimeException {

	public static final String title = "Service Unavailable";
	public static final ExceptionCode code = ExceptionCode.UNAVAILABLE;

	public ServiceUnavailableException(String message, Throwable cause, boolean enableSuppression,
									   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ServiceUnavailableException(String message, Throwable cause) {
		super(message, cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ServiceUnavailableException(String message) {
		super(message);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ServiceUnavailableException(Throwable cause) {
		super(cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ServiceUnavailableException(String message, ExceptionCode errorCode, String title, Exception cause) {
		super(message, errorCode, title, cause);
	}

	public ServiceUnavailableException(String message, ExceptionCode errorCode, String title) {
		super(message, errorCode, title);
	}

	public ServiceUnavailableException(String message, ExceptionCode errorCode) {
		super(message, errorCode);
		this.setTitle(title);
	}
}
