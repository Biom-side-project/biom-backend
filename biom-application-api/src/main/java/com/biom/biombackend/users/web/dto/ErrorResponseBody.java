package com.biom.biombackend.users.web.dto;

import com.biom.biombackend.users.exceptions.ErrorType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ErrorResponseBody {
    private static final String INTERNAL_SERVER_ERROR = "요청을 처리하지 못했습니다.";
    private transient final ObjectMapper objectMapper = new ObjectMapper();
    
    private ErrorResponse error;
    
    public ErrorResponseBody(ErrorResponse error) { this.error = error; }
    
    /* 사전에 정의된 예외들에 대한 응답 */
    public static ErrorResponseBody internalServerErrorOf(ExecutionException exception, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), INTERNAL_SERVER_ERROR, requestUri));
    }
    
    public static ErrorResponseBody internalServerErrorOf(InterruptedException exception, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), INTERNAL_SERVER_ERROR, requestUri));
    }
    
    public static ErrorResponseBody internalServerErrorOf(TimeoutException exception, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), INTERNAL_SERVER_ERROR, requestUri));
    }
    
    public static ErrorResponseBody internalServerErrorOf(Exception exception, String requestUri) {
        String message = exception.getMessage();
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), message == null ? INTERNAL_SERVER_ERROR : message, requestUri));
    }
    
    public static ErrorResponseBody detailedErrorsOf(ErrorType errorType, String message, List<?> errors, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(errorType.name(), message, errors, requestUri));
    }
    
    public static ErrorResponseBody detailedErrorsOf(Exception exception, String message, List<?> errors, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), message, errors, requestUri));
    }
    
    /* 사전에 정의되지 않은 예외를 상태코드로 응답하기 */
    public static ErrorResponseBody badRequestOf(Exception exception, Object message, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), message, requestUri));
    }
    
    public static ErrorResponseBody forbiddenOf(Exception exception, Object message, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), message, requestUri));
    }
    
    public static ErrorResponseBody notFoundOf(Exception exception, Object message, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), message, requestUri));
    }
    
    public static ErrorResponseBody methodNotAllowedOf(Exception exception, Object message, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(exception.getClass().getSimpleName(), message, requestUri));
    }
    
    public static ErrorResponseBody of(ErrorType errorType, Object message, String requestUri) {
        return new ErrorResponseBody(new ErrorResponse(errorType.getErrorCode(), message, requestUri));
    }
    
    public String asJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
    /* ********************** Getters for Jackson ********************** */
    public ErrorResponse getError() { return error; }
    
    @Override
    public String toString() {
        return "{\"ErrorResponseBody\":{" + "\"error\":" + error + "}}";
    }
}
