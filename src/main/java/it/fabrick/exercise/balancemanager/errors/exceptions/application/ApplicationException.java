package it.fabrick.exercise.balancemanager.errors.exceptions.application;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationException extends BaseRuntimeException {

	public static final String title = "Application exception";
	public static final ExceptionCode code = ExceptionCode.APPLICATION;

	public ApplicationException(String message, Throwable cause, boolean enableSuppression,
								boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ApplicationException(String message) {
		super(message);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
		this.setTitle(title);
		this.setExceptionCode(code);
	}

}
