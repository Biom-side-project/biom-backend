package com.biom.biombackend.users.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource(value = "classpath:env.${spring.profiles.active}.properties")
public class SpringConfig {
    
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
