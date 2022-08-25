package com.biom.biombackend.biom.data;

import com.biom.biombackend.BiomApplicationConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("local")
@ContextConfiguration(classes = BiomApplicationConfiguration.class)
class KoreaRegionCodeRepositoryTest {
    
    @Autowired KoreaRegionCodeRepository koreaRegionCodeRepository;
    
    @Test
    @DisplayName("test01")
    void test01() {
        // given
        KoreaRegionCode region = koreaRegionCodeRepository.findBySidoNameAndSigunguNameAndEupmyeondongNameAndDongliName("서울특별시", null, null, null);
        // when
        
        // then
        System.out.println(region);
    }
    
}