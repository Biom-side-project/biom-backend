package com.biom.biombackend.biom.web;

import com.biom.biombackend.biom.features.biom.BiomService;
import com.biom.biombackend.biom.features.biom.ReportAnom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BiomController {
    
    private final BiomService biomService;
    
    @PostMapping("/api/v1/biom/biom")
    public ResponseEntity<String> biom(){
        return null;
    }
    
    @PostMapping("/api/v1/biom/anom")
    public ResponseEntity<String> anom(){
        return null;
    }
}
