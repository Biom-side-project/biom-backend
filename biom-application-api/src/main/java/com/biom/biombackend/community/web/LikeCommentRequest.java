package com.biom.biombackend.community.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class LikeCommentRequest {
    @NotNull(message = "commentId 가 없습니다.")
    private String commentId;
}
