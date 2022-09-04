package com.biom.biombackend.users.features.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@Slf4j
@Component
class DefaultJwtManager implements JwtManager {
    
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 30 * 1000L;   // 30분 // 30초
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 60 * 60 * 24 * 1000L; // 7일
    private static final String USER_ID_NAME = "userId";
    
    private String secretKeyString;
    private final Key secretKey;
    
    public DefaultJwtManager(@Value("${users.jwt.secret}") String secret) {
        log.debug("SpringJwtManager initiating with secret");
        this.secretKeyString = secret;
        if (secretKeyString.length() != 4) {
            throw new IllegalArgumentException("the secret key string must be length of 4");
        }
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyString);
        this.secretKey = new SecretKeySpec(keyBytes, ALGORITHM.getJcaName());
    }
    
    @Override
    public String createAccessToken(CreateAccessToken command){
        Objects.requireNonNull(command.getEmail(), "email is required");
        String token = doCreateToken(command.getEmail(), command.getUserId(), ACCESS_TOKEN_EXPIRE_TIME);
        log.debug("created access token");
        return token;
    }
    
    @Override
    public String createRefreshToken(CreateRefreshToken command){
        Objects.requireNonNull(command.getEmail(), "email is required");
        String token = doCreateToken(command.getEmail(), command.getUserId(), REFRESH_TOKEN_EXPIRE_TIME);
        log.debug("created refresh token");
        return token;
    }
    
    private String doCreateToken(String email, Long userId, long expirationTime) {
        return Jwts.builder().setSubject(email).claim("userId", userId)
                   .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                   .signWith(ALGORITHM, secretKey).compact();
    }
    
    @Override
    public String getExpireTimeFromToken(String token) {
        Objects.requireNonNull(token, "token is required");
        String expirationTime = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration()
                            .toInstant().atZone(TimeZone.getDefault().toZoneId()).toString();
        log.debug("resolved token expirationTime: {}", expirationTime);
        return expirationTime;
    }
    
    @Override
    public String resolveUsername(String token) {
        Objects.requireNonNull(token);
        String subject = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        log.debug("resolved subject");
        return subject;
    }
    
    @Override
    public Long resolveUserId(String token) {
        Long userId = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(USER_ID_NAME, Long.class);
        log.debug("resolved userId");
        return userId;
    }
    
    /*
    * 토큰을 검증한다.
    * 토큰이 파싱이 가능하면 true 를 반환한다.
    * Expected Exceptions
    *   JwtException 클래스 상속 예외들
    * */
    @Override
    public boolean validateToken(String token) {
        boolean isValid = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token) != null;
        log.debug("validated Token: {}", isValid);
        return isValid;
    }
}
