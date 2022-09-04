package com.biom.biombackend.biom.features.region;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude // 리 단위가 null 이어도 반환함.
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
