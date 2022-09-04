package com.biom.biombackend.community.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LeaveCommentRequest {
    @NotNull(message = "{validation.notNull.community.content}")
    private String content;
    @NotNull(message = "{validation.notNull.community.regionCode}")
    private Long regionCode;
    
    @Override
    public String toString() {
        return "{\"LeaveCommentRequest\":{" + "\"content\":\"" + ((content != null) ? (content + "\"") : null) + ",\"regionCode\":" + regionCode + "}}";
    }
}
