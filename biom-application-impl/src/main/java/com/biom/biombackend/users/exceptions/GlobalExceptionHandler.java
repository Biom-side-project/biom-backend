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
        return ResponseEntity.internalServerError().body(ErrorResponseBody.internalServerErrorOf(exception, request.getRequestURI()));
    }
    
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseBody> on(ApplicationException exception, HttpServletRequest request) {
        HttpStatus statusCode = exception.getStatusCode();
        ErrorType errorType = exception.getErrorCode();
        String message = ms.getMessage(errorType.name(), null, "No available message", request.getLocale());
        log.debug("Application Exception errorCode: {}, statusCode: {}", errorType, statusCode);
        return ResponseEntity.status(statusCode)
                             .body(ErrorResponseBody.of(errorType, message, request.getRequestURI()));
    }
    
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponseBody> on(BindException exception, HttpServletRequest request) {
        log.debug("exception: {}", exception.getMessage());
        List<FieldError> allFieldErrors = exception.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body(ErrorResponseBody.detailedErrorsOf(exception,
                                                                MessageUtils.seeDetails(exception.getMessage()),
                                                                allFieldErrors.stream().map(this::createFieldErrorMap).collect(Collectors.toList()), request.getRequestURI()));
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseBody> on(HttpMessageNotReadableException exception, HttpServletRequest request) {
        log.debug("exception: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(ErrorResponseBody.badRequestOf(exception,
                                                                  ms.getMessage("jsonParseError", null, request.getLocale()),
                                                                  request.getRequestURI()));
    }
    
    private HashMap<String, String> createFieldErrorMap(FieldError fieldError) {
        HashMap<String, String> map = new HashMap<>();
        map.put("field", fieldError.getField());
        map.put("message", fieldError.getDefaultMessage());
        return map;
    }
}
