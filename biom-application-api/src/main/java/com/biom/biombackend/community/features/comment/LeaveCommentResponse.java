package com.biom.biombackend.community.features.comment;


import java.util.UUID;

public class LeaveCommentResponse {
    private final UUID commentId;
    
    private LeaveCommentResponse(UUID commentId) {
        this.commentId = commentId;
    }
    
    public static LeaveCommentResponse commentId(UUID commentId) {
        return new LeaveCommentResponse(commentId);
    }
    
    public UUID getCommentId() {
        return commentId;
    }
}
