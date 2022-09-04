package com.biom.biombackend.biom.web;

import com.biom.biombackend.biom.features.biom.*;
import com.biom.biombackend.users.features.jwt.AccessToken;
import com.biom.biombackend.users.features.jwt.JwtManager;
import com.biom.biombackend.users.web.dto.AnomRequest;
import com.biom.biombackend.users.web.dto.BiomRequest;
import com.biom.biombackend.users.web.dto.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BiomController {
    
    private final BiomService biomService;
    private final JwtManager jwtManager;
    private final MessageSource ms;
    
    @PostMapping("/api/v1/biom/biom")
    public ResponseEntity<SuccessResponseBody> biom(@AccessToken String accessToken,
                                                    @RequestBody BiomRequest request,
                                                    HttpServletRequest httpRequest){
        log.debug("accessToken: {}", accessToken);
        ReportBiomResponse response = biomService.handle(ReportBiom.builder()
                                                                 .userId(jwtManager.resolveUserId(accessToken))
                                                                 .regionCode(request.getRegionCode()).build());
        String message = ms.getMessage("biom.biomed", null, httpRequest.getLocale());
        if (response.getType().equals(BiomType.AlreadyBiomed)){
            message = ms.getMessage("biom.already_biomed", null, httpRequest.getLocale());
        }
        return ResponseEntity.ok().body(SuccessResponseBody.builder().message(message).data(response).build());
    }
    
    @PostMapping("/api/v1/biom/anom")
    public ResponseEntity<SuccessResponseBody> anom(@AccessToken String accessToken,
                                                    @RequestBody AnomRequest request,
                                                    HttpServletRequest httpRequest){
        log.debug("accessToken: {}", accessToken);
        biomService.handle(ReportAnom.builder()
                                     .userId(jwtManager.resolveUserId(accessToken))
                                     .regionCode(request.getRegionCode()).build());
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                .message(ms.getMessage("biom.anomed", null, httpRequest.getLocale())).build());
    }
    
    @GetMapping("/api/v1/biom/region")
    public ResponseEntity<SuccessResponseBody> getBiomCount(@RequestParam String regionCode,
                                                            HttpServletRequest httpRequest){
        long count = biomService.handle(GetRegionalBiomCount.builder().regionCode(regionCode).build());
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                .message(ms.getMessage("biom.region", null, httpRequest.getLocale()))
                                                .data(count).build());
    }
    
    @GetMapping("/api/v1/biom/proportion")
    public ResponseEntity<SuccessResponseBody> getBiomProportion(@RequestParam Long regionCode,
                                                                 HttpServletRequest httpRequest) {
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                           .message(ms.getMessage("biom.proportion", null, httpRequest.getLocale()))
                                                           .data(biomService.handle(GetBiomProportion.builder()
                                                                                                     .regionCode(regionCode)
                                                                                                     .build()))
                                                           .build());
    }
}
