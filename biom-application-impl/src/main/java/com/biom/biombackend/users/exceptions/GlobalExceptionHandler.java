package com.biom.biombackend.users.exceptions;

import com.biom.biombackend.users.web.dto.ErrorResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(999)
class GlobalExceptionHandler {
    
    private final MessageSource ms;
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseBody> on(Exception exception, HttpServletRequest request) {
        log.error("Global exception: {}", exception.toString());
        exception.printStackTrace();
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
    
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponseBody> on(BindException exception, HttpServletRequest request) {
        log.debug("exception: {}", exception.getMessage());
        List<FieldError> allFieldErrors = exception.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body(ErrorResponseBody.badRequestOf(exception, allFieldErrors.stream().map(this::createFieldErrorMap).collect(Collectors.toList()), request.getRequestURI()));
    }
    
    private HashMap<String, String> createFieldErrorMap(FieldError fieldError) {
        HashMap<String, String> map = new HashMap<>();
        map.put("field", fieldError.getField());
        map.put("message", fieldError.getDefaultMessage());
        return map;
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseBody> on(HttpMessageNotReadableException exception, HttpServletRequest request) {
        log.debug("exception: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(ErrorResponseBody.badRequestOf(exception,
                                                                  ms.getMessage("jsonParseError", null, request.getLocale()),
                                                                  request.getRequestURI()));
    }
}
