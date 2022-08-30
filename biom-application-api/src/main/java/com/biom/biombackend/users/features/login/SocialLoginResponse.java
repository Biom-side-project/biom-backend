package com.biom.biombackend.users.features.login;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SocialLoginResponse {
    private String accessToken;
    private String refreshToken;
    private String accessExpireTime;
    private String refreshExpireTime;
    
    @Override
    public String toString() {
        return "{\"SocialLoginResponse\":{" + "" + ((accessToken != null) ? ("\"accessToken\":\"" + accessToken + "\"") : ("\"accessToken\":" + null)) + "," + ((refreshToken != null) ? ("\"refreshToken\":\"" + refreshToken + "\"") : ("\"refreshToken\":" + null)) + "," + ((accessExpireTime != null) ? ("\"accessExpireTime\":\"" + accessExpireTime + "\"") : ("\"accessExpireTime\":" + null)) + "," + ((refreshExpireTime != null) ? ("\"refreshExpireTime\":\"" + refreshExpireTime + "\"") : ("\"refreshExpireTime\":" + null)) + "}}";
    }
}
