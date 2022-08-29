package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportAnom {
    private Long userId;
    private Long regionCode;
    
    @Override
    public String toString() {
        return "ReportAnom{" + "userId=" + userId + ", regionCode=" + regionCode + '}';
    }
}
