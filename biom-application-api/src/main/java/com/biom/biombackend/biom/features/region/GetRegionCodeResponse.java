package com.biom.biombackend.biom.features.region;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRegionCodeResponse {
    private Long regionCode;
    private String sidoName;
    private String sigunguName;
    private String eupmyeondongName;
    private String dongliName;
    
    @Override
    public String toString() {
        return "{\"GetRegionCodeResponse\":{" + "\"regionCode\":" + regionCode + ", \"sidoName\":" + ((sidoName != null) ? ("\"" + sidoName + "\"") : null) + ", \"sigunguName\":" + ((sigunguName != null) ? ("\"" + sigunguName + "\"") : null) + ", \"eupmyeondongName\":" + ((eupmyeondongName != null) ? ("\"" + eupmyeondongName + "\"") : null) + ", \"dongliName\":" + ((dongliName != null) ? ("\"" + dongliName + "\"") : null) + "}}";
    }
}
