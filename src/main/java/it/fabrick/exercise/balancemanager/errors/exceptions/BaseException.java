package it.fabrick.exercise.balancemanager.errors.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseException extends Exception {
	ExceptionCode exceptionCode;
	String title;
	Exception cause;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression,
						 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message, ExceptionCode exceptionCode, String title) {
		super(message);
		this.exceptionCode = exceptionCode;
		this.title = title;
	}

	public BaseException(String message, ExceptionCode exceptionCode) {
		super(message);
		this.exceptionCode = exceptionCode;
	}

	public BaseException(String message, ExceptionCode exceptionCode, String title, Exception cause) {
		super(message);
		this.exceptionCode = exceptionCode;
		this.title = title;
		this.cause = cause;
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

}
