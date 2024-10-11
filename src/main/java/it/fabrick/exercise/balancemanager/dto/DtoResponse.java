package it.fabrick.exercise.balancemanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.fabrick.exercise.balancemanager.errors.exceptions.BaseException;
import it.fabrick.exercise.balancemanager.errors.exceptions.BaseRuntimeException;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude
public class DtoResponse<T> extends RepresentationModel<DtoResponse<T>> {
	@Builder.Default
	private boolean success = true;
	@Builder.Default
	private DtoError error = null;
	private T payload;
	@JsonIgnore
	private HttpStatus httpStatus;

	public static <T> DtoResponse<T> ok(T body) {
		return DtoResponse.<T>builder()
			.httpStatus(HttpStatus.OK)
			.payload(body)
			.build();
	}

	public static <T> DtoResponse<T> ok(T body, HttpStatus httpStatus) {
		return DtoResponse.<T>builder()
			.httpStatus(httpStatus)
			.payload(body)
			.build();
	}

	public static <T> DtoResponse<T> ko() {
		DtoError dtoError = DtoError.ko();
		return DtoResponse.<T>builder()
			.success(false)
			.error(dtoError)
			.httpStatus(dtoError.getCode().getStatus())
			.build();
	}

	public static <T> DtoResponse<T> ko(BaseRuntimeException exception) {
		DtoError dtoError = DtoError.fromBaseRuntimeException(exception);
		return DtoResponse.<T>builder()
			.success(false)
			.error(dtoError)
			.httpStatus(dtoError.getCode().getStatus())
			.build();
	}

	public static <T> DtoResponse<T> ko(BaseException exception) {
		DtoError dtoError = DtoError.fromBaseException(exception);
		return DtoResponse.<T>builder()
			.success(false)
			.error(dtoError)
			.httpStatus(dtoError.getCode().getStatus())
			.build();
	}

	public static <T> DtoResponse<T> ko(String message) {
		DtoError dtoError = DtoError.ko(message);
		return DtoResponse.<T>builder()
			.success(false)
			.error(dtoError)
			.httpStatus(dtoError.getCode().getStatus())
			.build();
	}

	public static <T> DtoResponse<T> ko(DtoError dtoError) {
		return DtoResponse.<T>builder()
			.success(false)
			.error(dtoError)
			.httpStatus(dtoError.getCode().getStatus())
			.build();
	}

	public static <T> DtoResponse<T> ko(String message, HttpStatus httpStatus) {
		return DtoResponse.<T>builder()
			.success(false)
			.error(DtoError.ko(message))
			.httpStatus(httpStatus)
			.build();
	}

	public static <T> DtoResponse<T> ko(DtoError dtoError, HttpStatus status) {
		return DtoResponse.<T>builder()
			.success(false)
			.error(dtoError)
			.httpStatus(status)
			.build();
	}


}
