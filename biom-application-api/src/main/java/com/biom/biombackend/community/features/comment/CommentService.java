package com.biom.biombackend.community.features.comment;

import java.util.UUID;

public interface CommentService {
    UUID handle(LeaveComment command);
    
    GetCommentsInARegionResponse handle(GetCommentsInARegion command);
    
    void handle(LikeComment command);
}
