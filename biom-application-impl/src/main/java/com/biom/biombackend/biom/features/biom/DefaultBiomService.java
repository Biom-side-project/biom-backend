package com.biom.biombackend.biom.features.biom;

import com.biom.biombackend.biom.data.*;
import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
class DefaultBiomService implements BiomService{
    
    private final BiomRepository biomRepository;
    private final KoreaRegionCodeRepository regionCodeRepository;
    private final BiomUserRepository userRepository;
    private final AnomRepository anomRepository;
    
    @Override
    public void handle(ReportBiom command) {
        log.debug("handling command: {}", command);
        KoreaRegionCode region = regionCodeRepository.getReferenceById(Long.valueOf(command.getRegionCode()));
        BiomUser user = userRepository.getReferenceById(command.getUserId());
        Biom biom = Biom.builder().biomId(UUID.randomUUID()).user(user).regionCode(region).build();
        biomRepository.save(biom);
        log.debug("saved Biom: {}", biom);
    }
    
    @Override
    public void handle(ReportAnom command) {
        log.debug("handling command: {}", command);
        KoreaRegionCode region = regionCodeRepository.getReferenceById(Long.valueOf(command.getRegionCode()));
        BiomUser user = userRepository.getReferenceById(command.getUserId());
        Anom anom = Anom.builder().anomId(UUID.randomUUID()).user(user).regionCode(region).build();
        Anom save = anomRepository.save(anom);
        log.debug("saved Anom: {}", save);
    }
}
