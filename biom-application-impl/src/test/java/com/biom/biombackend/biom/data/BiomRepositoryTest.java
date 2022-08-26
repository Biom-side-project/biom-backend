package com.biom.biombackend.biom.data;

import com.biom.biombackend.BiomApplicationConfiguration;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("local")
@ContextConfiguration(classes = BiomApplicationConfiguration.class)
class BiomRepositoryTest {
    
    @Autowired BiomRepository biomRepository;
    @Autowired
    BiomUserRepository userRepository;
    @Autowired KoreaRegionCodeRepository regionCodeRepository;
    long regionCode1 = 11L;
    
    
    @BeforeEach
    void before() {
        UUID biomId = UUID.randomUUID();
        UUID biomId02 = UUID.randomUUID();
        KoreaRegionCode regionCode = KoreaRegionCode.builder().regionCode(regionCode1).build();
        BiomUser user = BiomUser.builder().build();
        regionCodeRepository.save(regionCode);
        userRepository.save(user);
        Biom biom01 = Biom.builder().biomId(biomId).regionCode(regionCode).user(user).build();
        Biom biom02 = Biom.builder().biomId(biomId02).regionCode(regionCode).user(user).build();
        biomRepository.save(biom01);
        biomRepository.save(biom02);
    }
    
    @RepeatedTest(10)
    @DisplayName("test01")
    void test01() {
        // given
        List<Biom> all = biomRepository.findAll();
        System.out.println("총 개수" + all.size());
        System.out.println("----------------------");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime interval = LocalDateTime.now().minus(1000L, ChronoUnit.MINUTES);
        System.out.println(now);
        System.out.println(interval);
        
        long counted = biomRepository.countByRegionCodeAndBetweenTimeInterval(regionCode1, interval, LocalDateTime.now());
    
        // when
        System.out.println(counted);
        
        // then
        assertThat(counted).isEqualTo(2L);
    }
}