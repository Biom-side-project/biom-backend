package com.biom.biombackend.community.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LeaveCommentRequest {
    private String content;
    private Long regionCode;
    
    @Override
    public String toString() {
        return "LeaveCommentRequest{" + "content='" + content + '\'' + ", regionCode=" + regionCode + '}';
    }
}
