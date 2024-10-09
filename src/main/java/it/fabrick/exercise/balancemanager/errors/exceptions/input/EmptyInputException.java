package it.fabrick.exercise.balancemanager.errors.exceptions.input;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;

public class EmptyInputException extends BaseRuntimeException {
	public static final String title = "Empty Input";
	public static final ExceptionCode code = ExceptionCode.EMPTY;

	public EmptyInputException(String message, Throwable cause, boolean enableSuppression,
							   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public EmptyInputException(String message, Throwable cause) {
		super(message, cause);

		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public EmptyInputException(String message) {
		super(message);

		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public EmptyInputException(Throwable cause) {
		super(cause);

		this.setTitle(title);
		this.setExceptionCode(code);
	}
}
