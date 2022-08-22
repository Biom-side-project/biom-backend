package com.biom.biombackend.users.features.login;

public interface SocialLoginService {
    SocialLoginResponse loginNaver(SocialLoginRequest request);
    SocialLoginResponse loginGoogle(SocialLoginRequest request);
}
