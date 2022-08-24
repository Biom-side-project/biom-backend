package com.biom.biombackend.users.exceptions;

import com.biom.biombackend.users.web.dto.ErrorResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
