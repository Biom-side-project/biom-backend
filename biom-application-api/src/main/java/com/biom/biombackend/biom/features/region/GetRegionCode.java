package com.biom.biombackend.biom.features.region;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRegionCode {
    private String sidoName;
    private String sigunguName;
    private String eupmyeondongName;
    private String dongliName;
    
    @Override
    public String toString() {
        return "{\"GetRegionCode\":{" + "\"sidoName\":" + ((sidoName != null) ? ("\"" + sidoName + "\"") : null) + ", \"sigunguName\":" + ((sigunguName != null) ? ("\"" + sigunguName + "\"") : null) + ", \"eupmyeondongName\":" + ((eupmyeondongName != null) ? ("\"" + eupmyeondongName + "\"") : null) + ", \"dongliName\":" + ((dongliName != null) ? ("\"" + dongliName + "\"") : null) + "}}";
    }
}
