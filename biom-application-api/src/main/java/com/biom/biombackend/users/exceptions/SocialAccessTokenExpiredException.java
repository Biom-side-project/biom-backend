package com.biom.biombackend.users.exceptions;

import org.springframework.http.HttpStatus;

public class SocialAccessTokenExpiredException extends ApplicationException{
    
    public SocialAccessTokenExpiredException() {
        super(ErrorType.SocialAccessTokenExpired, HttpStatus.BAD_REQUEST);
    }
}
