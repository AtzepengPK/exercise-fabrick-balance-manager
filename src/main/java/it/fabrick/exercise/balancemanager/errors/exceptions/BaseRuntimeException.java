package it.fabrick.exercise.balancemanager.errors.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseRuntimeException extends RuntimeException {
	ExceptionCode exceptionCode;
	String title;
	Exception cause;

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(String message, Throwable cause, boolean enableSuppression,
								boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseRuntimeException(String message, ExceptionCode exceptionCode, String title) {
		super(message);
		this.exceptionCode = exceptionCode;
		this.title = title;
	}

	public BaseRuntimeException(String message, ExceptionCode exceptionCode) {
		super(message);
		this.exceptionCode = exceptionCode;
	}

	public BaseRuntimeException(String message, ExceptionCode exceptionCode, String title, Exception cause) {
		super(message);
		this.exceptionCode = exceptionCode;
		this.title = title;
		this.cause = cause;
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}

}
