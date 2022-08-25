package com.biom.biombackend.biom.features.biom;

public interface BiomService {
    
    ReportBiomResponse handle(ReportBiom command);
    ReportAnomResponse handle(ReportAnom command);

}
