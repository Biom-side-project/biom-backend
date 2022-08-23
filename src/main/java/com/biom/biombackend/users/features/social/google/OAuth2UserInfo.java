package com.biom.biombackend.users.features.social.google;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OAuth2UserInfo {
    private String email;
    private String profile;
}
