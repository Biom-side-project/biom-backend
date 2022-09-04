package com.biom.biombackend.biom.web;

import com.biom.biombackend.biom.features.region.GetRegionCode;
import com.biom.biombackend.biom.features.region.RegionService;
import com.biom.biombackend.users.web.dto.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegionController {
    
    private final RegionService regionService;
    private final MessageSource ms;
    
    @GetMapping("/api/v1/region/region-code")
    public ResponseEntity<SuccessResponseBody> getRegionCode(@RequestParam String sido,
                                                             @Nullable @RequestParam String sigungu,
                                                             @Nullable @RequestParam String eupmyeondong,
                                                             @Nullable @RequestParam String dongli,
                                                             HttpServletRequest httpRequest) {
        GetRegionCode command = GetRegionCode.builder().sidoName(sido).sigunguName(sigungu).eupmyeondongName(eupmyeondong)
                                           .dongliName(dongli).build();
        SuccessResponseBody body = SuccessResponseBody.builder()
                                                      .status(200)
                                                      .message(ms.getMessage("region.region_code", null, httpRequest.getLocale()))
                                                       .data(regionService.handle(command)).build();
        return ResponseEntity.ok().body(body);
    }
}
