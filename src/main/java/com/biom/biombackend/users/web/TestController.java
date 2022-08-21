package com.biom.biombackend.users.web;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/health")
    public String health(@Nullable @RequestParam String string){
        return "health check... received string: " + string;
    }
}
