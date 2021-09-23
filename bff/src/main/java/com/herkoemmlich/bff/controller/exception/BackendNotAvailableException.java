package com.herkoemmlich.bff.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "backend call failed")
public class BackendNotAvailableException extends RuntimeException {
}
