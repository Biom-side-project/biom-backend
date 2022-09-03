package com.biom.biombackend.users.exceptions;

public enum ErrorType {
    RefreshTokenNotFound("RefreshTokenNotFound"),
    ;
    
    private final String errorCode;
    
    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() { return errorCode; }
}
