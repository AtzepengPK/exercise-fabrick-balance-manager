package it.fabrick.exercise.balancemanager.dto;

import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<DtoResponse<?>> {

	@Override
	public boolean supports(@Nonnull MethodParameter returnType, @Nonnull Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public DtoResponse<?> beforeBodyWrite(DtoResponse<?> body, @Nonnull MethodParameter returnType, @Nonnull MediaType selectedContentType, @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType, @Nonnull ServerHttpRequest request, @Nonnull ServerHttpResponse response) {
		if (body != null) {
			response.setStatusCode(body.getHttpStatus());
		}
		return body;
	}
}
