package com.biom.biombackend.users.features.login;

import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import com.biom.biombackend.users.data.Role;
import com.biom.biombackend.users.features.jwt.CreateAccessToken;
import com.biom.biombackend.users.features.jwt.CreateRefreshToken;
import com.biom.biombackend.users.features.jwt.JwtManager;
import com.biom.biombackend.users.features.social.JacksonOAuthAttributes;
import com.biom.biombackend.users.features.social.SocialProvider;
import com.biom.biombackend.users.features.social.google.GetUserInfo;
import com.biom.biombackend.users.features.social.google.GoogleClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
class DefaultSocialLoginService implements SocialLoginService {
    
    private final JwtManager jwtManager;
    private final GoogleClient googleClient;
    private final BiomUserRepository biomUserRepository;
    
    @Override
    public SocialLoginResponse loginNaver(SocialLoginRequest request) {
        return SocialLoginResponse.builder()
                                  .accessToken("awetaw")
                                  .refreshToken("refref").build();
    }
    
    @Override
    @Transactional
    public SocialLoginResponse loginGoogle(SocialLoginRequest request) {
        JacksonOAuthAttributes userInfo = googleClient.getUserInfo(GetUserInfo.builder().accessToken(request.getAccessToken()).build());
        log.debug("userInfo: {}", userInfo);
    
        // 유저 정보를 조회하여 없으면 회원가입 시킨다.
        BiomUser targetUser = biomUserRepository.getByEmail(userInfo.getEmail());
        if (targetUser == null) {
            BiomUser userEntity = BiomUser.builder().email(userInfo.getEmail()).socialType(SocialProvider.GOOGLE)
                                          .role(Role.ROLE_MEMBER).pictureUri(userInfo.getProfileImageUrl()).username(userInfo.getName()).build();
            biomUserRepository.save(userEntity);
            log.info("회원가입 되었습니다.: {}", userEntity);
        }
        
        // 있으면 바로 토큰을 발급하여 반환한다.
        SocialLoginResponse response = createSocialLoginResponse(userInfo);
        log.info("{} 님의 로그인 정보를 반환합니다.: {}", userInfo.getName(), response);
        return response;
    }
    
    private SocialLoginResponse createSocialLoginResponse(JacksonOAuthAttributes userInfo) {
        String accessToken = jwtManager.createAccessToken(CreateAccessToken.builder().email(userInfo.getEmail()).build());
        String refreshToken = jwtManager.createRefreshToken(CreateRefreshToken.builder().email(userInfo.getEmail()).build());
        return SocialLoginResponse.builder()
                                  .accessToken(accessToken)
                                  .refreshToken(refreshToken)
                                  .accessExpireTime(jwtManager.getExpireTimeFromToken(accessToken))
                                  .refreshExpireTime(jwtManager.getExpireTimeFromToken(refreshToken)).build();
    }
    
    
}
