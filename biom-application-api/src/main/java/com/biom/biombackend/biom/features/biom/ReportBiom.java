package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportBiom {
    private String userId;
    private String regionCode;
}