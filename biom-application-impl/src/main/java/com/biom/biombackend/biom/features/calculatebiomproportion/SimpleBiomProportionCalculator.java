package com.biom.biombackend.biom.features.calculatebiomproportion;

import com.biom.biombackend.biom.data.AnomRepository;
import com.biom.biombackend.biom.data.BiomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
@Slf4j
class SimpleBiomProportionCalculator implements BiomProportionCalculator{
    private static final long BIOM_PROPORTION_TIME_INTERVAL_MINUTE = 10L;
    
    private final BiomRepository biomRepository;
    private final AnomRepository anomRepository;
    
    @Override
    public double calculateBiomProportion(Long regionCode) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeBefore = now.minus(BIOM_PROPORTION_TIME_INTERVAL_MINUTE, ChronoUnit.MINUTES);
        long biomCount = biomRepository.countByRegionCodeAndBetweenTimeInterval(regionCode, timeBefore, now);
        long anomCount = anomRepository.countByRegionCodeAndBetweenTimeInterval(regionCode, timeBefore, now);
        
        double proportion = (double) biomCount / (anomCount + biomCount);
        if (anomCount + biomCount == 0) {
            proportion  = 0;
            log.debug("anomCount + biomCount: {}", anomCount+biomCount);
        }
        log.info("calculated biom proportion: {}", proportion);
        return proportion;
        
    }
}
