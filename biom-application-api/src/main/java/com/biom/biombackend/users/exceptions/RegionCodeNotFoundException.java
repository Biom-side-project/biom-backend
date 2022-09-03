package com.biom.biombackend.users.exceptions;

import org.springframework.http.HttpStatus;

public class RegionCodeNotFoundException extends ApplicationException{
    
    public RegionCodeNotFoundException() {
        super(ErrorType.RegionCodeNotFound, HttpStatus.NOT_FOUND);
    }
}
