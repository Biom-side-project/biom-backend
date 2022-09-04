package com.biom.biombackend.biom.features.biom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude
public class GetBiomProportionResponse {
    private double biomProportion;
    private Long regionCode;
    private String sidoName;
    private String sigunguName;
    private String eupmyeondongName;
    private String dongliName;
    
    @Override
    public String toString() {
        return "{\"GetBiomProportionResponse\":{" + "\"biomProportion\":" + biomProportion + ", \"regionCode\":" + regionCode + ", \"sidoName\":" + ((sidoName != null) ? ("\"" + sidoName + "\"") : null) + ", \"sigunguName\":" + ((sigunguName != null) ? ("\"" + sigunguName + "\"") : null) + ", \"eupmyeondongName\":" + ((eupmyeondongName != null) ? ("\"" + eupmyeondongName + "\"") : null) + ", \"dongliName\":" + ((dongliName != null) ? ("\"" + dongliName + "\"") : null) + "}}";
    }
}
