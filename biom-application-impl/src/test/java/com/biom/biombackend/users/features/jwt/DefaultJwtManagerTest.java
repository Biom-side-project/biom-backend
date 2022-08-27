package com.biom.biombackend.users.features.jwt;

import com.biom.biombackend.BiomApplicationConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = BiomApplicationConfiguration.class)
class DefaultJwtManagerTest {
    
    @Autowired JwtManager jwtManager;
    private String email = "random@gmail.com";
    private Long userId = 123L;
    
    @Test
    @DisplayName("토큰에 포함되는 이메일을 뽑을 수 있는지?")
    void test01() {
        // given
        CreateAccessToken command = CreateAccessToken.builder().userId(userId).email(email).build();
        
        // when
        String accessToken = jwtManager.createAccessToken(command);
    
        // then
        String result = jwtManager.resolveUsername(accessToken);
        assertThat(result).isEqualTo(email);
    }
    
    @Test
    @DisplayName("user id 를 뽑을 수 있는지 테스트")
    void resolveUserIdTest() {
        // given
        CreateAccessToken command = CreateAccessToken.builder().userId(userId).email(email).build();
    
        // when
        String accessToken = jwtManager.createAccessToken(command);
        
        // then
        Long result = jwtManager.resolveUserId(accessToken);
        assertThat(result).isEqualTo(userId);
    }
    
    @Test
    @DisplayName("시간 포맷 확인")
    void test03() {
        // given
        CreateAccessToken command = CreateAccessToken.builder().userId(userId).email(email).build();
        // when
        String accessToken = jwtManager.createAccessToken(command);
    
        // then
        String expireTimeFromToken = jwtManager.getExpireTimeFromToken(accessToken);
        System.out.println("expireTimeFromToken = " + expireTimeFromToken);
    }
}