package com.biom.biombackend.biom.features.region;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportUsersLocation {
    private String userId;
    private String regionCode;
    
    @Override
    public String toString() {
        return "{\"ReportUsersLocation\":{" + "" + ((userId != null) ? ("\"userId\":\"" + userId + "\"") : ("\"userId\":" + null)) + "," + ((regionCode != null) ? ("\"regionCode\":\"" + regionCode + "\"") : ("\"regionCode\":" + null)) + "}}";
    }
}
