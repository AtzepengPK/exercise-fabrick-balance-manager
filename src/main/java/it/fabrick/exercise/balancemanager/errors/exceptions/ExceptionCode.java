package it.fabrick.exercise.balancemanager.errors.exceptions;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;


@Getter
public enum ExceptionCode {

	APPLICATION("AP50001", HttpStatus.INTERNAL_SERVER_ERROR),
	TECHNICAL("TE50001", HttpStatus.INTERNAL_SERVER_ERROR),
	DEVELOPER("DE50301", HttpStatus.INTERNAL_SERVER_ERROR),
	UNAVAILABLE("UN50001", HttpStatus.SERVICE_UNAVAILABLE),
	MISSING("MI40001", HttpStatus.BAD_REQUEST),
	EMPTY("EI40001", HttpStatus.BAD_REQUEST),
	UNAUTHORIZED("UE40101", HttpStatus.UNAUTHORIZED),
	CONFLICT("CO40901", HttpStatus.CONFLICT),
	NOT_FOUND("NF40401", HttpStatus.NOT_FOUND),
	WRONG_INPUT("WI40001", HttpStatus.BAD_REQUEST);


	private static final Map<String, ExceptionCode> BY_CODE = new HashMap<>();

	static {
		for (ExceptionCode e : values()) {
			BY_CODE.put(e.code, e);
		}
	}

	@JsonValue
	private final String code;
	private final HttpStatus status;

	ExceptionCode(String code, HttpStatus status) {
		this.code = code;
		this.status = status;
	}

	public static ExceptionCode valueOfCode(String code) {
		return BY_CODE.get(code);
	}

}
