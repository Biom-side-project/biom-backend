package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetBiomProportion {
    private Long regionCode;
    
    @Override
    public String toString() {
        return "{\"GetBiomProportion\":{" + "\"regionCode\":" + regionCode + "}}";
    }
}
