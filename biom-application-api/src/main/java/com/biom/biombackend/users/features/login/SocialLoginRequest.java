package com.biom.biombackend.users.features.login;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class SocialLoginRequest {
    private String accessToken;
}
