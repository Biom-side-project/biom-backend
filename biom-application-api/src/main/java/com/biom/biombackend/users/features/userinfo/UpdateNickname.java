package com.biom.biombackend.users.features.userinfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateNickname {
    private Long userId;
    private String newNickname;
    
    @Override
    public String toString() {
        return "{\"UpdateNickname\":{" + "\"userId\":" + userId + ", \"newNickname\":" + ((newNickname != null) ? ("\"" + newNickname + "\"") : null) + "}}";
    }
}
