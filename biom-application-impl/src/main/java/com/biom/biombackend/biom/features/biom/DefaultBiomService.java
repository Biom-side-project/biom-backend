package com.biom.biombackend.biom.features.biom;

import com.biom.biombackend.biom.data.BiomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultBiomService implements BiomService{
    
    private final BiomRepository biomRepository;
    
    @Override
    public ReportBiomResponse handle(ReportBiom command) {
        return null;
    }
    
    @Override
    public ReportAnomResponse handle(ReportAnom command) {
        return null;
    }
}
