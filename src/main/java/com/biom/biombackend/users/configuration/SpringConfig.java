package com.biom.biombackend.users.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource(value = "classpath:env.${spring.config.activate.on-profile}.properties") // spring.profiles.active 는 주입시점이 늦다.
public class SpringConfig {
    
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
