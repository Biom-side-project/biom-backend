package com.biom.biombackend.users.features.userinfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserInfo {
    private Long userId;
    
    @Override
    public String toString() {
        return "GetUserInfo{" + "userId=" + userId + '}';
    }
}
