package com.biom.biombackend.users.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@ConditionalOnNotWebApplication
public class ConditionalSpringConfig {
    
    @Bean
    ObjectMapper objectMapper() { return new ObjectMapper(); }
    
    /*
    * Spring Security 에서 ant matcher 를 사용할 때 사용되는 빈
    * 이름이 mvcHandlerMappingIntrospector 이어야 함.
    * 해당 이름의 빈이 중복 등록되면 안되므로 서브 프로젝트가 빌드될때만 등록되도록 설정.
    */
    @Bean
    HandlerMappingIntrospector mvcHandlerMappingIntrospector() { return new HandlerMappingIntrospector(); }
}
