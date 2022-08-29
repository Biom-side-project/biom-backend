package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportBiom {
    private Long userId;
    private Long regionCode;
}
