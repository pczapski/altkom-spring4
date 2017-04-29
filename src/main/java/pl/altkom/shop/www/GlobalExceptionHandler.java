package pl.altkom.shop.www;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public String handleError1(MultipartException e, HttpServletRequest request) {

		// redirectAttributes.addFlashAttribute("message",
		// e.getCause().getMessage());
		return "redirect:" + request.getRequestURI().replace(request.getContextPath(), "");

	}
}
