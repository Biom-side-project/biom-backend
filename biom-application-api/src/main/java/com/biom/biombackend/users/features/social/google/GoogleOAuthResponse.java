package com.biom.biombackend.users.features.social.google;

import lombok.*;

/*
* google 리소스 서버로 부터 받은 유저 정보.*/
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GoogleOAuthResponse {
    private String id;
    private String email;
    private String verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;
}
