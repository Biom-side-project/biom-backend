package com.biom.biombackend.users.features.jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReissueTokens {
    private String refreshToken;
    private String userAgent;
    
    @Override
    public String toString() {
        return "{\"ReissueTokens\":{" + "" + ((refreshToken != null) ? ("\"refreshToken\":\"" + refreshToken + "\"") : ("\"refreshToken\":" + null)) + "," + ((userAgent != null) ? ("\"userAgent\":\"" + userAgent + "\"") : ("\"userAgent\":" + null)) + "}}";
    }
}
