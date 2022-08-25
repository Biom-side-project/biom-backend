package com.biom.biombackend.biom.web;

import com.biom.biombackend.biom.features.region.GetRegionCode;
import com.biom.biombackend.biom.features.region.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegionController {
    
    private final RegionService regionService;
    
    @GetMapping("/api/v1/region/region-code")
    public String getRegionCode(@RequestParam String sido,
                                @Nullable @RequestParam String sigungu,
                                @Nullable @RequestParam String eupmyendong,
                                @Nullable @RequestParam String dongli) {
        return regionService.handle(GetRegionCode.builder()
                                                 .sidoName(sido)
                                                 .sigunguName(sigungu)
                                                 .eupmyeondongName(eupmyendong)
                                                 .dongliName(dongli).build()).getRegionCode().toString();
    }
}
