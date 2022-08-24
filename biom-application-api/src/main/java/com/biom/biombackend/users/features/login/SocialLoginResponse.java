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
}
