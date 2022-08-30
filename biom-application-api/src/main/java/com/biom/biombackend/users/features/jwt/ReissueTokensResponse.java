package com.biom.biombackend.users.features.jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReissueTokensResponse {
    private String accessToken;
    private String refreshToken;
    private String accessExpireTime;
    private String refreshExpireTime;
    
    @Override
    public String toString() {
        return "{\"ReissueTokensResponse\":{" + "" + ((accessToken != null) ? ("\"accessToken\":\"" + accessToken + "\"") : ("\"accessToken\":" + null)) + "," + ((refreshToken != null) ? ("\"refreshToken\":\"" + refreshToken + "\"") : ("\"refreshToken\":" + null)) + "," + ((accessExpireTime != null) ? ("\"accessExpireTime\":\"" + accessExpireTime + "\"") : ("\"accessExpireTime\":" + null)) + "," + ((refreshExpireTime != null) ? ("\"refreshExpireTime\":\"" + refreshExpireTime + "\"") : ("\"refreshExpireTime\":" + null)) + "}}";
    }
}
