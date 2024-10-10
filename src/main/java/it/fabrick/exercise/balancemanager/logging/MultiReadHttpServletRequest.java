//package it.fabrick.exercise.balancemanager.logging;
//
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//
//public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
//
//	private byte[] bytesStream;
//	private boolean streamReaded = false;
//
//	public MultiReadHttpServletRequest(HttpServletRequest request) {
//		super(request);
//	}
//
//	@Override
//	public ServletInputStream getInputStream() throws IOException {
//		// if input stream was not read will be passed original servletinputstream
//		if (!streamReaded) {
//			return super.getInputStream();
//		} else {
//			return new CustomServletInputStream(getInputStreamCopy());
//		}
//	}
//
//	public byte[] getInputStreamCopy() throws IOException {
//		if (!streamReaded) {
//			bytesStream = super.getInputStream().readAllBytes();
//			streamReaded = true;
//		}
//		return bytesStream;
//	}
//
//	@Override
//	public BufferedReader getReader() throws IOException {
//		return new BufferedReader(new InputStreamReader(getInputStream()));
//	}
//
//}
