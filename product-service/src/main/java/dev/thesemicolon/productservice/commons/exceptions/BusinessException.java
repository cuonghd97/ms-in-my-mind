package dev.thesemicolon.productservice.commons.exceptions;

public class BusinessException extends Exception{
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
