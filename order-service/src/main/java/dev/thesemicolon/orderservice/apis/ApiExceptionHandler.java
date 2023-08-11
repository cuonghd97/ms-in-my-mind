package dev.thesemicolon.orderservice.apis;

import dev.thesemicolon.orderservice.commons.exceptions.AuthenticateException;
import dev.thesemicolon.orderservice.commons.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import the.semicolon.orderservice.server.model.ErrorResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(AuthenticateException.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public ErrorResponse handleAuthenticationException(Exception ex, WebRequest request) {
    return new ErrorResponse().message(ex.getMessage());
  }

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBusinessException(Exception ex, WebRequest request) {
    return new ErrorResponse().message(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleAllException(Exception ex, WebRequest request) {
    return new ErrorResponse().message(ex.getMessage());
  }
}
