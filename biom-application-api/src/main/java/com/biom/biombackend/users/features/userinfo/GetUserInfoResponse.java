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
        return "{\"GetUserInfoResponse\":{" + "" + ((email != null) ? ("\"email\":\"" + email + "\"") : ("\"email\":" + null)) + "," + ((username != null) ? ("\"username\":\"" + username + "\"") : ("\"username\":" + null)) + "," + ((pictureUri != null) ? ("\"pictureUri\":\"" + pictureUri + "\"") : ("\"pictureUri\":" + null)) + "," + ((nickname != null) ? ("\"nickname\":\"" + nickname + "\"") : ("\"nickname\":" + null)) + "}}";
    }
}
