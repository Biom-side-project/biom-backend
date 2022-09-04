package com.biom.biombackend.users.exceptions;

import org.springframework.http.HttpStatus;

public class FailedToResolveAccessTokenException extends ApplicationException {
    public FailedToResolveAccessTokenException() {
        super(ErrorType.FailedToResolveAccessToken, HttpStatus.UNAUTHORIZED);
    }
}
