package it.fabrick.exercise.balancemanager.errors.exceptions.input;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;

public class WrongInputException extends BaseRuntimeException {
	public static final String title = "Wrong Input";
	public static final ExceptionCode code = ExceptionCode.WRONG_INPUT;

	public WrongInputException(String message, Throwable cause, boolean enableSuppression,
							   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public WrongInputException(String message, Throwable cause) {
		super(message, cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public WrongInputException(String message) {
		super(message);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public WrongInputException(Throwable cause) {
		super(cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}
}
