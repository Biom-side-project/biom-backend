package com.biom.biombackend.users.exceptions;

import com.biom.biombackend.users.web.dto.ErrorResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(1)
class ServletExceptionHandler {
    
    private final MessageSource ms;
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseBody> on(HttpRequestMethodNotSupportedException exception,
                                                HttpServletRequest request) {
        log.debug("exception: ~ {} ~ was thrown", exception.toString());
        String message = ms.getMessage("HttpRequestMethodNotSupportedException", null, request.getLocale());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                             .body(ErrorResponseBody.methodNotAllowedOf(exception,
                                                                        message,
                                                                        request.getRequestURI()));
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseBody> on(NoHandlerFoundException exception,
                                                HttpServletRequest request) {
        log.debug("exception: ~ {} ~ was thrown", exception.toString());
        String message = ms.getMessage("NoHandlerFoundException", new String[]{request.getRequestURI()}, request.getLocale());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ErrorResponseBody.notFoundOf(exception,
                                                                message,
                                                                request.getRequestURI()));
    }
}
