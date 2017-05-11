package pl.altkom.shop.lib;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
public class ControllerExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ModelAndView handle(final Exception exception, final HttpServletRequest request) {
		Map<String, Object> model = new HashMap();
		model.put("exception", exception);
		return new ModelAndView("error", model);
	}
}
