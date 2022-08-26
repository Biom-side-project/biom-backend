package com.biom.biombackend.users.features.social.google;

import com.biom.biombackend.users.excepions.SocialAccessTokenExpired;
import com.biom.biombackend.users.features.social.JacksonOAuthAttributes;
import com.biom.biombackend.users.features.social.SocialProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
class DefaultGoogleClient implements GoogleClient {
    
    @Value("${users.oauth2.google.resource-url}")
    private String resourceServerUrl;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    @SneakyThrows
    public JacksonOAuthAttributes getUserInfo(GetUserInfo command){
        log.debug("handling: {}", command);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + command.getAccessToken());
        String uri = UriComponentsBuilder.fromUriString(resourceServerUrl).encode().build().toUriString();
    
        RequestEntity<Void> request = RequestEntity.get(uri).headers(headers).build();
        String body;
        JsonNode jsonNode;
        JacksonOAuthAttributes attributes;
        try {
            body = restTemplate.exchange(request, String.class).getBody();
            jsonNode = Objects.requireNonNull(objectMapper.readTree(body));
            attributes = JacksonOAuthAttributes.of(SocialProvider.GOOGLE).attributes(jsonNode).build();
            log.debug("Google attributes: {}", attributes);
            log.debug("attributes.getEmail(): {}", attributes.getEmail());
    
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new SocialAccessTokenExpired("구글 리소스 서버로부터 유저 정보를 받지 못했습니다.", 404);
        }
        
        log.debug("recieved attribute: {}", attributes);
        return attributes;
    }
}
