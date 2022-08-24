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
}