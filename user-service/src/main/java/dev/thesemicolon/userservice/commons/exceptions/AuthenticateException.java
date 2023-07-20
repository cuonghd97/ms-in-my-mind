package dev.thesemicolon.userservice.commons.exceptions;

public class AuthenticateException extends Exception{
    public AuthenticateException(String errorMessage) {
        super(errorMessage);
    }
}
