package com.biom.biombackend.biom.features.biom;

public interface BiomService {
    
    ReportBiomResponse handle(ReportBiom command);
    void handle(ReportAnom command);
    
    long handle(GetRegionalBiomCount command);
    
    GetBiomProportionResponse handle(GetBiomProportion command);
}
