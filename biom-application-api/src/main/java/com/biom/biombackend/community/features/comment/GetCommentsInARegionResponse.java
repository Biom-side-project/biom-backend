package com.biom.biombackend.community.features.comment;

import com.biom.biombackend.biom.features.region.GetRegionCodeResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetCommentsInARegionResponse {
    private GetRegionCodeResponse region;
    private List<SingleComment> comments;
    private int total;
}
