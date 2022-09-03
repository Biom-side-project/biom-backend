package com.biom.biombackend.users.exceptions;

public class FailedToResolveAccessTokenException extends ExceptionWithStatusCode {
    public FailedToResolveAccessTokenException() {
        super("Access Token could not be resolved.", 401);
    }
}
