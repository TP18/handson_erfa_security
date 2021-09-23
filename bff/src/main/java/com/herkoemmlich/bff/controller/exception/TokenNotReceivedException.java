package com.herkoemmlich.bff.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "on behalf token not issued")
public class TokenNotReceivedException extends RuntimeException {
}
