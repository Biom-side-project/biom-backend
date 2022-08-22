package com.biom.biombackend.users.features.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SpringSocialLoginService implements SocialLoginService {
    
    @Override
    public SocialLoginResponse loginNaver(SocialLoginRequest request) {
        return SocialLoginResponse.builder()
                                  .accessToken("asdf")
                                  .refreshToken("refref").build();
    }
}
