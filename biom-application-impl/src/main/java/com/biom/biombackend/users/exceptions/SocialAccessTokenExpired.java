package com.biom.biombackend.users.exceptions;

public class SocialAccessTokenExpired extends ExceptionWithStatusCode{
    
    public SocialAccessTokenExpired(String message, int statusCode) {
        super(message, statusCode);
    }
}
