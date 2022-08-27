package com.biom.biombackend;

import com.biom.biombackend.users.features.jwt.JwtManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
class BiomBackendApplicationTests {

    @Test
    void contextLoads() {
    }
    
    @Test
    @DisplayName("어떻게 출력되는지 확인해보자")
    void test02() {
        // given
        String localDateTime = LocalDateTime.now().toString();
        String instant = Instant.now().toString();
        // when
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("instant = " + instant);
        // then
    }
}
