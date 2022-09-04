package com.biom.biombackend.biom.features.biom;

import com.biom.biombackend.biom.data.*;
import com.biom.biombackend.biom.features.calculatebiomproportion.BiomProportionCalculator;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import com.biom.biombackend.users.exceptions.RegionCodeNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
class DefaultBiomService implements BiomService{
    
    private static final long BIOM_PROPORTION_TIME_INTERVAL_MINUTE = 30L;
    private static final long BIOM_REPORT_TIME_INTERVAL_MINUTE = 10L;
    
    private final BiomRepository biomRepository;
    private final KoreaRegionCodeRepository regionCodeRepository;
    private final BiomUserRepository userRepository;
    private final AnomRepository anomRepository;
    private final BiomProportionCalculator calculator;
    
    @Override
    @Transactional
    public ReportBiomResponse handle(ReportBiom command) {
        log.debug("handling command: {}", command);
        Long regionCode = command.getRegionCode();
        if (!regionCodeRepository.existsByRegionCode(regionCode)) {
            throw new RegionCodeNotFoundException();
        }
        KoreaRegionCode region = regionCodeRepository.getReferenceById(regionCode);
        BiomUser user = userRepository.getReferenceById(command.getUserId());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime past = now.minus(BIOM_REPORT_TIME_INTERVAL_MINUTE, ChronoUnit.MINUTES);
        Optional<Biom> optionalBiom = biomRepository.findByUserAndRegionCodeAndCreatedAtBetween(user, region, past, now);
        ReportBiomResponse response;
        if (optionalBiom.isPresent()){
            response = createAlreadyBiomedResponse(now, optionalBiom.get());
            return response;
        }
        Biom biom = Biom.builder().biomId(UUID.randomUUID()).user(user).regionCode(region).build();
        Biom savedBiom = biomRepository.save(biom);
        log.debug("saved Biom: {}", biom);
        response = ReportBiomResponse.builder().biomId(savedBiom.getBiomId())
                                                     .createdAt(savedBiom.getCreatedAt()).type(BiomType.BiomedSuccessful)
                                                     .build();
        return response;
    }
    
    private ReportBiomResponse createAlreadyBiomedResponse(LocalDateTime now, Biom biom) {
        ReportBiomResponse response;
        UUID biomId = biom.getBiomId();
        LocalDateTime createdAt = biom.getCreatedAt();
        LocalDateTime nextAvailableBiomTime = createdAt.plus(BIOM_REPORT_TIME_INTERVAL_MINUTE, ChronoUnit.MINUTES);
        long timeLeft = now.until(nextAvailableBiomTime, ChronoUnit.SECONDS);
        response = ReportBiomResponse.builder().biomId(biomId).createdAt(createdAt)
                                                     .type(BiomType.AlreadyBiomed)
                                                     .timeLeft(new TimeLeft(ChronoUnit.SECONDS, timeLeft))
                                                     .build();
        log.info("response: {}", response);
        return response;
    }
    
    @Override
    @Transactional
    public void handle(ReportAnom command) {
        log.debug("handling command: {}", command);
        Long regionCode = command.getRegionCode();
        if (!regionCodeRepository.existsByRegionCode(regionCode)) {
            throw new RegionCodeNotFoundException();
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
            throw new RegionCodeNotFoundException();
        }
        LocalDateTime timeBefore = LocalDateTime.now().minus(BIOM_PROPORTION_TIME_INTERVAL_MINUTE, ChronoUnit.MINUTES);
        long biomCount = biomRepository.countByRegionCodeAndBetweenTimeInterval(regionCode, timeBefore, LocalDateTime.now());
        log.debug("{} 의 biomCount 는 : {}", command.getRegionCode(), biomCount);
        return biomCount;
    }
    
    @Override
    public GetBiomProportionResponse handle(GetBiomProportion command) {
        log.debug("handling command: {}", command);
        Long regionCode = command.getRegionCode();
        Optional<KoreaRegionCode> optionalRegion = regionCodeRepository.findById(regionCode);
        if (optionalRegion.isEmpty()) {
            throw new RegionCodeNotFoundException();
        }
        KoreaRegionCode region = optionalRegion.get();
        double proportion = calculator.calculateBiomProportion(regionCode);
        GetBiomProportionResponse response = createGetBiomProportionResponse(region, proportion);
        log.debug("biom proportion response: {}", response);
        return response;
    }
    
    private GetBiomProportionResponse createGetBiomProportionResponse(KoreaRegionCode koreaRegionCode, double proportion) {
        return GetBiomProportionResponse.builder()
                                        .biomProportion(proportion)
                                        .regionCode(koreaRegionCode.getRegionCode())
                                        .sidoName(koreaRegionCode.getSidoName())
                                        .sigunguName(koreaRegionCode.getSigunguName())
                                        .eupmyeondongName(koreaRegionCode.getEupmyeondongName())
                                        .dongliName(koreaRegionCode.getDongliName()).build();
    }
}
