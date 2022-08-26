package com.biom.biombackend.users.exceptions;

import com.biom.biombackend.users.excepions.ExceptionWithStatusCode;
import com.biom.biombackend.users.web.dto.ErrorResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseBody> on(Exception exception, HttpServletRequest request) {
        log.error("exception: {}", exception.toString());
        return ResponseEntity.internalServerError().body(ErrorResponseBody.of(exception, request.getRequestURI()));
    }
    
    @ExceptionHandler(ExceptionWithStatusCode.class)
    public ResponseEntity<ErrorResponseBody> on(ExceptionWithStatusCode exception, HttpServletRequest request) {
        log.debug("exception: {}", exception.toString());
        switch (exception.getStatusCode()) {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(ErrorResponseBody.badRequestOf(exception, exception.getMessage(), request.getRequestURI()));
            case 404:
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(ErrorResponseBody.notFoundOf(exception, exception.getMessage(), request.getRequestURI()));
        }
        return ResponseEntity.internalServerError().body(ErrorResponseBody.of(exception, request.getRequestURI()));
    }
}
