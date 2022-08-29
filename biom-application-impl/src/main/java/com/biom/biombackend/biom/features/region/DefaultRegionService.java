package com.biom.biombackend.biom.features.region;

import com.biom.biombackend.biom.data.KoreaRegionCode;
import com.biom.biombackend.biom.data.KoreaRegionCodeRepository;
import com.biom.biombackend.users.exceptions.RegionNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultRegionService implements RegionService{
    
    private final KoreaRegionCodeRepository regionCodeRepository;
    
    
    @Override
    public GetRegionCodeResponse handle(GetRegionCode command) {
        log.debug("command: {}", command);
        String sidoName = command.getSidoName();
        String sigunguName = command.getSigunguName() == null ? null : command.getSigunguName();
        String eupmyeondongName = command.getEupmyeondongName() == null ? null : command.getEupmyeondongName();
        String dongliName = command.getDongliName() == null ? null : command.getDongliName();
        KoreaRegionCode entity = regionCodeRepository.findBySidoNameAndSigunguNameAndEupmyeondongNameAndDongliName(sidoName, sigunguName, eupmyeondongName, dongliName);
        log.debug("entity: {}", entity);
        if (entity == null) {
            throw new RegionNotFoundException();
        }
        return GetRegionCodeResponse.builder().regionCode(entity.getRegionCode()).build();
    }
}
