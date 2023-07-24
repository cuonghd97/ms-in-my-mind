package dev.thesemicolon.productservice.commons.exceptions;

public class AuthenticateException extends Exception{
    public AuthenticateException(String errorMessage) {
        super(errorMessage);
    }
}
