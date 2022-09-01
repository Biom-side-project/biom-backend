package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRegionalBiomCount {
    private String regionCode;
    
    @Override
    public String toString() {
        return "{\"GetRegionalBiomCount\":{" + "\"regionCode\":" + ((regionCode != null) ? ("\"" + regionCode + "\"") : null) + "}}";
    }
}
