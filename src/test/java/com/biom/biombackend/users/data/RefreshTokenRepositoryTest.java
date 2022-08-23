package com.biom.biombackend.users.data;

import com.biom.biombackend.users.data.RefreshTokenEntity;
import com.biom.biombackend.users.data.RefreshTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class RefreshTokenRepositoryTest {
    
    @Autowired private RefreshTokenRepository refreshTokenRepository;
    
    @Test
    @DisplayName("findByRefreshTokenValue 메서드 테스트")
    void test01() {
        // given
        String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnbWxkbmpzMjUwMEBnbWFpbC5jb20iLCJleHAiOjE2NjE4NTk5OTZ9.UodS66mnQ3CDhyxKFlaljYpYDPzj2v8Nf3UjX2IfniE";
        RefreshTokenEntity entity = RefreshTokenEntity.builder().refreshTokenValue(refreshToken).subject("sub").userAgent("ua")
                                                      .build();
        // when
        refreshTokenRepository.save(entity);
        // then
        RefreshTokenEntity foundEntity = refreshTokenRepository.findByRefreshTokenValue(refreshToken);
        assertThat(foundEntity).isNotNull();
        assertThat(foundEntity).isEqualTo(entity);
    }
    
    
}