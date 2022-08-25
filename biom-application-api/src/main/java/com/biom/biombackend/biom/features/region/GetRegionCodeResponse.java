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
}
