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
        return "{\"GetUserInfoResponse\":{" + "\"email\":" + ((email != null) ? ("\"" + email + "\"") : null) + ", \"username\":" + ((username != null) ? ("\"" + username + "\"") : null) + ", \"pictureUri\":" + ((pictureUri != null) ? ("\"" + pictureUri + "\"") : null) + ", \"nickname\":" + ((nickname != null) ? ("\"" + nickname + "\"") : null) + "}}";
    }
}
