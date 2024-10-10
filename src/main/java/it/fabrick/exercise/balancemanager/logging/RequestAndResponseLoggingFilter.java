//package it.fabrick.exercise.balancemanager.logging;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//@Slf4j
//public class RequestAndResponseLoggingFilter extends OncePerRequestFilter {
//
//	// @formatter:off
//	private  final List<MediaType> visibleTypes =
//		Arrays.asList(MediaType.valueOf("text/*"),
//									MediaType.APPLICATION_FORM_URLENCODED,
//									MediaType.APPLICATION_JSON,
//									MediaType.APPLICATION_XML,MediaType.valueOf("application/*+json"),
//									MediaType.valueOf("application/*+xml"),
//									MediaType.MULTIPART_FORM_DATA);
//
//	protected void afterRequest( ContentCachingResponseWrapper response, long serviceStart) {
//		try {
//			if (log.isInfoEnabled()) {
//				logResponse(response, "<", serviceStart);
//			}
//		} catch (Exception e) {
//			log.error("Throw error when log service response", e);
//		}
//	}
//
//	protected void beforeRequest(MultiReadHttpServletRequest request) throws IOException {
//		try {
//			if (log.isInfoEnabled()) {
//				logRequestHeader(request, ">");
//				logRequestBody(request, ">");
//			}
//		} catch (Exception e) {
//			log.error("Throw error when log service request", e);
//		}
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		if (isAsyncDispatch(request)) {
//			filterChain.doFilter(request, response);
//		} else {
//			doFilterWrapped(ServletUtil.wrapRequest(request), ServletUtil.wrapResponse(response), filterChain);
//		}
//	}
//
//	protected void doFilterWrapped(MultiReadHttpServletRequest request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
//		long serviceStart = System.currentTimeMillis();
//		try {
//			beforeRequest(request);
//			filterChain.doFilter(request, response);
//		} finally {
//			afterRequest( response, serviceStart);
//			response.copyBodyToResponse();
//		}
//	}
//
//	// @formatter:on
//	private String generateHeaderJsonString(HttpServletRequest request) {
//		Set<String> ignoreHeader = new HashSet<>();
//		// add here headers to ignore
//		ignoreHeader.add("cookie");
//		StringBuilder headers = new StringBuilder();
//		headers.append("{");
//		// @formatter:off
//			Collections.list(request.getHeaderNames()).stream().
//			//filter cookie name not included into ignoreHeader
//				filter(headerName-> ignoreHeader.stream().noneMatch(ignorableHeader-> ignorableHeader.equals(headerName)))
//				//for each remained header add value to headers string
//				.forEach(headerName -> Collections.list(request.getHeaders(headerName))
//						.forEach(headerValue -> headers.append(headerName).append(":").append(headerValue).append(",")));
//			// @formatter:on
//		return headers.substring(0, headers.length() - 1).concat("}");
//	}
//
//	private void logContent(byte[] content, String contentType, String contentEncoding, String prefix, boolean isRequest) throws Exception {
//		MediaType mediaType = MediaType.valueOf(contentType);
//		boolean visible = visibleTypes.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
//		if (visible) {
//			try {
//				String contentString = new String(content, contentEncoding);
//				contentString = appLogUtils.filterBody(contentString, mediaType);
//				contentString = appLogUtils.limitBody(contentString, isRequest);
//				log.info("{} body     :{}", prefix, contentString);
//			} catch (UnsupportedEncodingException e) {
//				log.info("{} [{} bytes content]", prefix, content.length);
//			}
//		} else {
//			log.info("{} [{} bytes content]", prefix, content.length);
//		}
//	}
//
//	private void logRequestBody(MultiReadHttpServletRequest request, String prefix) throws Exception {
//		if (appLogUtils.isRequestBodyToLog()) {
//			byte[] content = request.getInputStreamCopy();
//			if (content.length > 0) {
//				logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix, true);
//			}
//		}
//	}
//
//	private void logRequestHeader(MultiReadHttpServletRequest request, String prefix) {
//		log.info("==========================>>");
//		String queryString = request.getQueryString();
//		if (queryString == null) {
//			log.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
//		} else {
//			log.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
//		}
//		// log headers
//		log.info("{} headers  : {}", prefix, generateHeaderJsonString(request));
//		log.info("{} remote   : {}", prefix, request.getRemoteAddr());
//	}
//
//	private void logResponse(ContentCachingResponseWrapper response, String prefix, long serviceStart) throws Exception {
//		int status = response.getStatus();
//		log.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
//		response.getHeaderNames().forEach(headerName -> response.getHeaders(headerName).forEach(headerValue -> log.info("{} {}: {}", prefix, headerName, headerValue)));
//		byte[] content = response.getContentAsByteArray();
//		if (content.length > 0) {
//			logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix, false);
//		}
//		log.info("==========={}ms==========<<", System.currentTimeMillis() - serviceStart);
//	}
//
//}
