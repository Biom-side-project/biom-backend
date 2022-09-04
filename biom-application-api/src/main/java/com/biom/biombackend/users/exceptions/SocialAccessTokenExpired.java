package com.biom.biombackend.users.exceptions;

import org.springframework.http.HttpStatus;

public class SocialAccessTokenExpired extends ApplicationException{
    
    public SocialAccessTokenExpired() {
        super(ErrorType.SocialAccessTokenExpired, HttpStatus.BAD_REQUEST);
    }
}
