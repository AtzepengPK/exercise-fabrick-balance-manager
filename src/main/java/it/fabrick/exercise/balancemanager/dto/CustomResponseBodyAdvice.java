package it.fabrick.exercise.balancemanager.dto;

import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.Objects;

@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<DtoResponse<?>> {

	@Override
	public boolean supports(@Nonnull MethodParameter returnType, @Nonnull Class<? extends HttpMessageConverter<?>> converterType) {
		ResolvableType type = ResolvableType.forMethodParameter(returnType);

		Class<?> rawClass = type.getRawClass();
		return rawClass != null && (DtoResponse.class.isAssignableFrom(rawClass) || ResponseEntity.class.isAssignableFrom(rawClass)
			&& Arrays.stream(type.getGenerics()).anyMatch(t -> DtoResponse.class.isAssignableFrom(Objects.requireNonNull(t.getRawClass()))));
	}

	@Override
	public DtoResponse<?> beforeBodyWrite(DtoResponse<?> body, @Nonnull MethodParameter returnType, @Nonnull MediaType selectedContentType, @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType, @Nonnull ServerHttpRequest request, @Nonnull ServerHttpResponse response) {
		if (body != null) {
			response.setStatusCode(body.getHttpStatus());
		}
		return body;
	}
}
