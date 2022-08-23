package com.biom.biombackend.users.features.social.google;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class GetUserInfo {
    private String accessToken;
}
