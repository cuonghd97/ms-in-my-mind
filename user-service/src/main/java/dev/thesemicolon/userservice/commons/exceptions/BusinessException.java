package dev.thesemicolon.userservice.commons.exceptions;

public class BusinessException extends Exception{
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
