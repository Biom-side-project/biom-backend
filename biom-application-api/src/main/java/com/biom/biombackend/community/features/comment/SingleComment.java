package com.biom.biombackend.community.features.comment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SingleComment {
    private UUID commentId;
    private String content;
    private String name;
    private int likes;
    private LocalDateTime createdAt;
    
    @Override
    public String toString() {
        return "{\"SingleComment\":{" + "\"commentId\":\"" + commentId + "\"" + ", \"content\":\"" + content + "\"" + ", \"name\":\"" + name + "\"" + ", \"likes\":\"" + likes + "\"" + ", \"createdAt\":\"" + createdAt + "\"" + "}}";
    }
}
