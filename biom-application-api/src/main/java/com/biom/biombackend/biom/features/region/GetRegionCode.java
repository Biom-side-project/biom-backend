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
}