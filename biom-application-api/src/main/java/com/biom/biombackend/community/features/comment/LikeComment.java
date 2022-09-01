package com.biom.biombackend.community.features.comment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class LikeComment {
    private UUID commentId;
    private Long userId;
    
    @Override
    public String toString() {
        return "{\"LikeComment\":{" + "" + ((commentId != null) ? ("\"commentId\":\"" + commentId + "\"") : ("\"commentId\":" + null)) + ",\"userId\":" + userId + "}}";
    }
}
