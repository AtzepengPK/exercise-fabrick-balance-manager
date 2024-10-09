package it.fabrick.exercise.balancemanager.errors.exceptions.utils;

import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import it.fabrick.exercise.balancemanager.errors.exceptions.ExceptionCode;
import it.fabrick.exercise.balancemanager.errors.exceptions.application.*;
import it.fabrick.exercise.balancemanager.errors.exceptions.input.*;

public class BaseExceptionFactory {
	static private BaseExceptionFactory instance;

	private BaseExceptionFactory() {
	}

	public static BaseExceptionFactory getInstance() {
		if (null == instance) {
			instance = new BaseExceptionFactory();
		}
		return instance;
	}


	public BaseRuntimeException fromExceptionCode(ExceptionCode exceptionCode, String message) {
		return switch (exceptionCode) {
			case APPLICATION -> new ApplicationException(message);
			case DEVELOPER -> new DeveloperException(message);
			case TECHNICAL -> new TechnicalException(message);
			case MISSING -> new MissingInputException(message);
			case EMPTY -> new EmptyInputException(message);
			case UNAUTHORIZED -> new UnauthorizedException(message);
			case CONFLICT -> new ConflictException(message);
			case NOT_FOUND -> new NotFoundException(message);
			case WRONG_INPUT -> new WrongInputException(message);
			case UNAVAILABLE -> new ServiceUnavailableException(message);
			default -> new ApplicationException(message);
		};

	}
}
