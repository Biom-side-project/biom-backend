package com.biom.biombackend.users.web.dto;

public class SuccessResponseBody {
    
    private int status;
    private String message;
    private Object data;
    
    public SuccessResponseBody(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    public static Builder builder(){
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "{\"SuccessResponseBody\":{" + "\"status\":" + status + ", \"message\":" + ((message != null) ? ("\"" + message + "\"") : null) + ", \"data\":" + data + "}}";
    }
    
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public Object getData() { return data; }
    
    public static class Builder{
        private int status;
        private String message;
        private Object data;
        
        public Builder status(int status){
            this.status = status;
            return this;
        }
    
        public Builder message(String message){
            this.message = message;
            return this;
        }
    
        public Builder data(Object data){
            this.data = data;
            return this;
        }
        
        public SuccessResponseBody build(){
            return new SuccessResponseBody(this.status, this.message, this.data);
        }
    }
}
