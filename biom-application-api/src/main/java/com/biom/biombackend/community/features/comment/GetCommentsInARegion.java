package com.biom.biombackend.community.features.comment;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class GetCommentsInARegion {
    private Long regionCode;
    private Integer page;
    @Builder.Default
    private Integer size = 10;
    
    @Override
    public String toString() {
        return "{\"GetCommentsInARegion\":{" + "\"regionCode\":" + regionCode + ", \"page\":" + page + ", \"size\":" + size + "}}";
    }
}
