package com.biom.biombackend.users.features.jwt;

import com.biom.biombackend.users.data.RefreshTokenEntity;
import com.biom.biombackend.users.data.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
class DefaultReissueTokensHandler implements ReissueTokensHandler{
    
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtManager jwtManager;
    
    @Override
    @Transactional
    public ReissueTokensResponse handle(ReissueTokens command) {
        log.debug("handling {}", command);
        RefreshTokenEntity oldRefreshToken = refreshTokenRepository.findByRefreshTokenValue(command.getRefreshToken());
        log.debug("oldRefreshToken: {}", oldRefreshToken);
        if (oldRefreshToken == null) {
            throw new NullPointerException("존재하지 않는 토큰입니다.");
        }
        if (!oldRefreshToken.getUserAgent().equals(command.getUserAgent())){
            throw new IllegalArgumentException("User-Agent 값이 유효하지 않습니다.");
        }
        // 리프레시 토큰 발급
        String newAccessToken = jwtManager.createAccessToken(CreateAccessToken.builder().email(oldRefreshToken.getSubject())
                                                                           .build());
        String newRefreshToken = jwtManager.createRefreshToken(CreateRefreshToken.builder().email(oldRefreshToken.getSubject())
                                                                           .build());
        // 리프레시 토큰을 저장한다.
        // TODO: 이전 refresh token 을 삭제하는 로직
        saveNewRefreshToken(oldRefreshToken, newRefreshToken);
        ReissueTokensResponse response = createResponse(newAccessToken, newRefreshToken);
        log.info("재발급한 토큰을 반환합니다.: {}", response);
        return response;
    }
    
    private void saveNewRefreshToken(RefreshTokenEntity refreshToken, String newRefreshToken) {
        refreshTokenRepository.save(RefreshTokenEntity.builder().refreshTokenValue(newRefreshToken).subject(refreshToken.getSubject()).userAgent(
                refreshToken.getUserAgent()).build());
    }
    
    private ReissueTokensResponse createResponse(String newAccessToken, String newRefreshToken) {
        return ReissueTokensResponse.builder()
                                    .accessToken(newAccessToken)
                                    .refreshToken(newRefreshToken)
                                    .accessExpireTime(jwtManager.getExpireTimeFromToken(newAccessToken))
                                    .refreshExpireTime(jwtManager.getExpireTimeFromToken(newRefreshToken)).build();
    }
}
