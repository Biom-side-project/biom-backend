package com.biom.biombackend.community.exceptions;

import com.biom.biombackend.users.exceptions.ApplicationException;
import com.biom.biombackend.users.exceptions.ErrorType;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends ApplicationException {
    
    public CommentNotFoundException() {
        super(ErrorType.CommentNotFound, HttpStatus.NOT_FOUND);
    }
}
