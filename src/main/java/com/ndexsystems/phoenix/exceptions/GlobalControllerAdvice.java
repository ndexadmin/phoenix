// =============================================
package com.ndexsystems.phoenix.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ndexsystems.phoenix.controllers.BaseController;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(BaseController.UnauthenticatedException.class)
	public String handleUnauthenticated(BaseController.UnauthenticatedException ex) {
		return ex.getRedirect();
	}
}
