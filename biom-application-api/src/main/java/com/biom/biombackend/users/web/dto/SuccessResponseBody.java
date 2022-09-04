package com.biom.biombackend.users.web.dto;

public class SuccessResponseBody {
    
    private String message;
    private Object data;
    
    public SuccessResponseBody(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    
    public static Builder builder(){
        return new Builder();
    }
    
    @Override
    public String toString() {
        return "{\"SuccessResponseBody\":{" + "\"message\":" + ((message != null) ? ("\"" + message + "\"") : null) + ", \"data\":" + data + "}}";
    }
    
    public String getMessage() { return message; }
    public Object getData() { return data; }
    
    public static class Builder{
        private String message;
        private Object data;
        
        public Builder message(String message){
            this.message = message;
            return this;
        }
    
        public Builder data(Object data){
            this.data = data;
            return this;
        }
        
        public SuccessResponseBody build(){
            return new SuccessResponseBody(this.message, this.data);
        }
    }
}
