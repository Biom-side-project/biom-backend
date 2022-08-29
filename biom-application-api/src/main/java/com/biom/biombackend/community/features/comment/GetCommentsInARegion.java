package com.biom.biombackend.community.features.comment;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GetCommentsInARegion {
    private Long regionCode;
    
    @Override
    public String toString() {
        return "GetCommentsInARegion{" + "regionCode=" + regionCode + '}';
    }
}
