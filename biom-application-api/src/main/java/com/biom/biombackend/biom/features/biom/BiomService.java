package com.biom.biombackend.biom.features.biom;

public interface BiomService {
    
    void handle(ReportBiom command);
    void handle(ReportAnom command);

}
