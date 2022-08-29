package com.biom.biombackend.users.features.userinfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserInfoResponse {
    private String email;
    private String username;
    private String pictureUri;
    private String nickname;
    
    @Override
    public String toString() {
        return "{\"GetUserInfoResponse\":{" + "\"email\":\"" + email + "\"" + ", \"username\":\"" + username + "\"" + ", \"pictureUri\":\"" + pictureUri + "\"" + ", \"nickname\":\"" + nickname + "\"" + "}}";
    }
}
