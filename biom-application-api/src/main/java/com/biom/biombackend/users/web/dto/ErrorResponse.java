package com.biom.biombackend.users.web.dto;

class ErrorResponse {
    
    private final int status;
    private final String type;
    private final Object message;
    private final String requestUri;
    
    public ErrorResponse(String type, Object message, int status, String requestUri) {
        this.type = type;
        this.message = message;
        this.status = status;
        this.requestUri = requestUri;
    }
    
    /* ********************** Getters for Jackson ********************** */
    public String getType() { return type; }
    public Object getMessage() { return message; }
    public int getStatus() { return status; }
    public String getRequestUri() { return requestUri; }
    
    @Override
    public String toString() {
        return "{\"ErrorResponse\":{" + "\"status\":\"" + status + "\"" + ", \"type\":\"" + type + "\"" + ", \"message\":" + message + ", \"requestUri\":\"" + requestUri + "\"" + "}}";
    }
}
