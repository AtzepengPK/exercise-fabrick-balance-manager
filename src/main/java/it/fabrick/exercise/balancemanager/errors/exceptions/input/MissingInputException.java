package it.fabrick.exercise.balancemanager.errors.exceptions.input;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;

public class MissingInputException extends BaseRuntimeException {
	public static final String title = "Missing Input";
	public static final ExceptionCode code = ExceptionCode.MISSING;

	public MissingInputException(String message, Throwable cause, boolean enableSuppression,
								 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public MissingInputException(String message, Throwable cause) {
		super(message, cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public MissingInputException(String message) {
		super(message);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public MissingInputException(Throwable cause) {
		super(cause);

		this.setTitle(title);
		this.setExceptionCode(code);
	}
}
