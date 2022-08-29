package com.biom.biombackend.users.configuration;

import com.biom.biombackend.users.features.jwt.JwtManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

@Configuration
@PropertySource(value = "classpath:env.${spring.config.activate.on-profile}.properties") // spring.profiles.active 는 주입시점이 늦다.
@EnableJpaAuditing
@Slf4j
public class SpringConfig {
    
    @Bean RestTemplate restTemplate() { return new RestTemplate(); }
    
    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("현재 시간 = {}", new Date());
        log.info("currentTimeMillis = {}", new Date(System.currentTimeMillis()));
    }
}
