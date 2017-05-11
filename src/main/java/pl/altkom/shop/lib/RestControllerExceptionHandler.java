package pl.altkom.shop.lib;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {
	public static class ServerError {
		private String result = "error :-(";
		private String error;

		public ServerError(String error) {
			this.setError(error);
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getResult() {
			return result;
		}

	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServerError> handle(final Exception exception) {
		exception.printStackTrace();
		return ResponseEntity.ok(new ServerError(ExceptionUtils.getStackTrace(exception)));
	}
}
