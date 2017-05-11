package pl.altkom.shop.lib;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.ELRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class AjaxAwareLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static final RequestMatcher requestMatcher = new ELRequestMatcher(
			"hasHeader('X-Requested-With','XMLHttpRequest')");

	public AjaxAwareLoginUrlAuthenticationEntryPoint() {
	}

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException, ServletException {
		if (isPreflight(request)) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else if (isRestRequest(request)) {
			response.setHeader("Login-Screen", "true");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	private boolean isPreflight(HttpServletRequest request) {
		return "OPTIONS".equals(request.getMethod());
	}

	protected boolean isRestRequest(HttpServletRequest request) {
		return requestMatcher.matches(request);
	}
}
