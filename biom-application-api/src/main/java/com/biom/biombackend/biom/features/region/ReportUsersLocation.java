package com.biom.biombackend.biom.features.region;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportUsersLocation {
    private String userId;
    private String regionCode;
}
