package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetBiomProportionResponse {
    private double biomProportion;
    private Long regionCode;
    private String sidoName;
    private String sigunguName;
    private String eupmyeondongName;
    private String dongliName;
    
    @Override
    public String toString() {
        return "{\"GetBiomProportionResponse\":{" + "\"biomProportion\":\"" + biomProportion + "\"" + ", \"regionCode\":\"" + regionCode + "\"" + ", \"sidoName\":\"" + sidoName + "\"" + ", \"sigunguName\":\"" + sigunguName + "\"" + ", \"eupmyeondongName\":\"" + eupmyeondongName + "\"" + ", \"dongliName\":\"" + dongliName + "\"" + "}}";
    }
}
