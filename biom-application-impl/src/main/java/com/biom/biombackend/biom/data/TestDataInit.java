package com.biom.biombackend.biom.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Profile({"local", "test"})
@RequiredArgsConstructor
@Component
public class TestDataInit {
    
    @PostConstruct
    public void init(){
    }
    
}
