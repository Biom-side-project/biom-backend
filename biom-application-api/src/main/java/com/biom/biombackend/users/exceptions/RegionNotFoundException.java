package com.biom.biombackend.users.exceptions;

import org.springframework.http.HttpStatus;

public class RegionNotFoundException extends ApplicationException{
    
    public RegionNotFoundException() {
        super(ErrorType.RegionNotFound, HttpStatus.NOT_FOUND);
    }
}
