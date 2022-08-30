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
        return "{\"SingleComment\":{" + "" + ((commentId != null) ? ("\"commentId\":\"" + commentId + "\"") : ("\"commentId\":" + null)) + "," + ((content != null) ? ("\"content\":\"" + content + "\"") : ("\"content\":" + null)) + "," + ((name != null) ? ("\"name\":\"" + name + "\"") : ("\"name\":" + null)) + ",\"likes\":" + likes + "," + ((createdAt != null) ? ("\"createdAt\":\"" + createdAt + "\"") : ("\"createdAt\":" + null)) + "}}";
    }
}
