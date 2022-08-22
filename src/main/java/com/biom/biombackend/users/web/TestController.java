package com.biom.biombackend.users.web;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/")
    public String home(){
        return "health";
    }
    
    @GetMapping("/health")
    public String health(@Nullable @RequestParam String string){
        return "health check... received string: " + string;
    }
    
    @GetMapping("/test/error-test")
    public String error() throws Exception {
        new Any().throwError();
        return "health check... received string: ";
    }
    

    
    private static class Any{
        void throwError() throws Exception {
            throw new Exception("예시 에러 입니다.");
        }
    }
    
    
}
