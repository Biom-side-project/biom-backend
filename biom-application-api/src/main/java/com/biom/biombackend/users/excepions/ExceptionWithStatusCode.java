package com.biom.biombackend.users.excepions;

public class ExceptionWithStatusCode extends IllegalArgumentException{
    
    private final int statusCode;
    
    public ExceptionWithStatusCode(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
}
