package com.biom.biombackend.community.features.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LeaveComment {
    private Long userId;
    private Long regionCode;
    private String content;
    
    @Override
    public String toString() {
        return "{\"LeaveComment\":{" + "\"userId\":" + userId + ",\"regionCode\":" + regionCode + ",\"content\":\"" + ((content != null) ? (content + "\"") : null) + "}}";
    }
}
