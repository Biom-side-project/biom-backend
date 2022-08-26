package com.biom.biombackend.users.excepions;

public class SocialAccessTokenExpired extends ExceptionWithStatusCode{
    
    public SocialAccessTokenExpired(String message, int statusCode) {
        super(message, statusCode);
    }
}
