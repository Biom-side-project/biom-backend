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
        return "{\"GetRegionCode\":{" + "" + ((sidoName != null) ? ("\"sidoName\":\"" + sidoName + "\"") : ("\"sidoName\":" + null)) + "," + ((sigunguName != null) ? ("\"sigunguName\":\"" + sigunguName + "\"") : ("\"sigunguName\":" + null)) + "," + ((eupmyeondongName != null) ? ("\"eupmyeondongName\":\"" + eupmyeondongName + "\"") : ("\"eupmyeondongName\":" + null)) + "," + ((dongliName != null) ? ("\"dongliName\":\"" + dongliName + "\"") : ("\"dongliName\":" + null)) + "}}";
    }
}
