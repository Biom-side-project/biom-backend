package com.biom.biombackend.users.exceptions;

public class ExceptionWithStatusCode extends RuntimeException{
    
    private final int statusCode;
    
    public ExceptionWithStatusCode(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
}
