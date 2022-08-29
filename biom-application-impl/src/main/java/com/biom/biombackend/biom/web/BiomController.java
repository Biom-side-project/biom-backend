package com.biom.biombackend.biom.web;

import com.biom.biombackend.biom.features.biom.*;
import com.biom.biombackend.users.features.jwt.AccessToken;
import com.biom.biombackend.users.features.jwt.JwtManager;
import com.biom.biombackend.users.web.dto.AnomRequest;
import com.biom.biombackend.users.web.dto.BiomRequest;
import com.biom.biombackend.users.web.dto.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BiomController {
    
    private final BiomService biomService;
    private final JwtManager jwtManager;
    
    @PostMapping("/api/v1/biom/biom")
    public ResponseEntity<SuccessResponseBody> biom(@AccessToken String accessToken,
                                                    @RequestBody BiomRequest request){
        log.debug("accessToken: {}", accessToken);
        biomService.handle(ReportBiom.builder().userId(jwtManager.resolveUserId(accessToken))
                                   .regionCode(request.getRegionCode()).build());
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                .message("비옴 표시하였습니다.").build());
    }
    
    @PostMapping("/api/v1/biom/anom")
    public ResponseEntity<SuccessResponseBody> anom(@AccessToken String accessToken,
                                                    @RequestBody AnomRequest request){
        log.debug("accessToken: {}", accessToken);
        biomService.handle(ReportAnom.builder()
                                     .userId(jwtManager.resolveUserId(accessToken))
                                     .regionCode(request.getRegionCode()).build());
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                .message("안옴 표시하였습니다.").build());
    }
    
    @GetMapping("/api/v1/biom/region")
    public ResponseEntity<SuccessResponseBody> getBiomCount(@RequestParam String regionCode){
        long count = biomService.handle(GetRegionalBiomCount.builder().regionCode(regionCode).build());
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                .message("해당 지역의 비옴 개수를 반환합니다.")
                                                .data(count).build());
    }
    
    @GetMapping("/api/v1/biom/proportion")
    public ResponseEntity<SuccessResponseBody> getBiomProportion(@RequestParam Long regionCode) {
        return ResponseEntity.ok().body(SuccessResponseBody.builder().status(200).message("비옴 비율을 반환합니다.")
                                                           .data(biomService.handle(GetBiomProportion.builder()
                                                                                                     .regionCode(regionCode)
                                                                                                     .build()))
                                                           .build());
    }
}
