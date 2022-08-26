package com.biom.biombackend.biom.features.biom;

import com.biom.biombackend.biom.data.*;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import com.biom.biombackend.users.excepions.ExceptionWithStatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
class DefaultBiomService implements BiomService{
    
    private static final long TIME_INTERVAL = 10L;
    
    private final BiomRepository biomRepository;
    private final KoreaRegionCodeRepository regionCodeRepository;
    private final BiomUserRepository userRepository;
    private final AnomRepository anomRepository;
    
    @Override
    @Transactional
    public void handle(ReportBiom command) {
        log.debug("handling command: {}", command);
        Long regionCode = Long.valueOf(command.getRegionCode());
        if (!regionCodeRepository.existsByRegionCode(regionCode)) {
            throw new ExceptionWithStatusCode("존재하지 않는 지역 코드 입니다.", 400);
        }
        KoreaRegionCode region = regionCodeRepository.getReferenceById(regionCode);
        BiomUser user = userRepository.getReferenceById(command.getUserId());
        Biom biom = Biom.builder().biomId(UUID.randomUUID()).user(user).regionCode(region).build();
        biomRepository.save(biom);
        log.debug("saved Biom: {}", biom);
    }
    
    @Override
    @Transactional
    public void handle(ReportAnom command) {
        log.debug("handling command: {}", command);
        Long regionCode = Long.valueOf(command.getRegionCode());
        if (!regionCodeRepository.existsByRegionCode(regionCode)) {
            throw new ExceptionWithStatusCode("존재하지 않는 지역 코드 입니다.", 400);
        }
        KoreaRegionCode region = regionCodeRepository.getReferenceById(regionCode);
        BiomUser user = userRepository.getReferenceById(command.getUserId());
        Anom anom = Anom.builder().anomId(UUID.randomUUID()).user(user).regionCode(region).build();
        Anom save = anomRepository.save(anom);
        log.debug("saved Anom: {}", save);
    }
    
    @Override
    @Transactional
    public long handle(GetRegionalBiomCount command) {
        log.debug("handling command: {}", command);
        long regionCode = Long.parseLong(command.getRegionCode());
        if (!regionCodeRepository.existsByRegionCode(regionCode)) {
            throw new ExceptionWithStatusCode("존재하지 않는 지역 코드 입니다.", 400);
        }
        LocalDateTime timeBefore = LocalDateTime.now().minus(TIME_INTERVAL, ChronoUnit.MINUTES);
        log.debug("timeBefore: {}", timeBefore);
        long biomCount = biomRepository.countByRegionCodeAndBetweenTimeInterval(regionCode, timeBefore, LocalDateTime.now());
        log.debug("{} 의 biomCount 는 : {}", command.getRegionCode(), biomCount);
        return biomCount;
    }
}
