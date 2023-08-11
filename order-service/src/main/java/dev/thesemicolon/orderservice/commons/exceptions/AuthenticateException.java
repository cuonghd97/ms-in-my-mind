package dev.thesemicolon.orderservice.commons.exceptions;

public class AuthenticateException extends Exception {
  public AuthenticateException(String errorMessage) {
    super(errorMessage);
  }
}
