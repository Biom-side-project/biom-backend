package com.biom.biombackend.users.web;

import com.biom.biombackend.users.features.jwt.AccessToken;
import com.biom.biombackend.users.features.jwt.JwtManager;
import com.biom.biombackend.users.features.jwt.ReissueTokens;
import com.biom.biombackend.users.features.jwt.ReissueTokensHandler;
import com.biom.biombackend.users.features.login.ProcessLogin;
import com.biom.biombackend.users.features.login.SocialLoginRequest;
import com.biom.biombackend.users.features.login.SocialLoginService;
import com.biom.biombackend.users.features.userinfo.GetUserInfo;
import com.biom.biombackend.users.web.dto.UpdateNickNameRequest;
import com.biom.biombackend.users.features.userinfo.UpdateNickname;
import com.biom.biombackend.users.features.userinfo.UserService;
import com.biom.biombackend.users.web.dto.ReissueTokensRequest;
import com.biom.biombackend.users.web.dto.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final SocialLoginService socialLoginService;
    private final ReissueTokensHandler reissueTokensHandler;
    private final UserService userService;
    private final JwtManager jwtManager;
    private final MessageSource ms;

    @PostMapping("/api/v1/login/naver")
    public ResponseEntity<SuccessResponseBody> loginNaver(@RequestBody SocialLoginRequest request,
                                                          HttpServletRequest httpRequest){
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message(ms.getMessage("users.login.succeeded", null, httpRequest.getLocale()))
                                                    .data(socialLoginService.loginNaver(request)).build());
    }
    @PostMapping("/api/v1/login/google")
    public ResponseEntity<SuccessResponseBody> loginGoogle(@RequestHeader("User-Agent") String userAgent,
                                                           @RequestBody SocialLoginRequest request,
                                                           HttpServletRequest httpRequest) {
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message(ms.getMessage("users.login.succeeded", null, httpRequest.getLocale()))
                                                    .data(socialLoginService.loginGoogle(ProcessLogin.builder()
                                                                                                     .accessToken(request.getAccessToken())
                                                                                                     .userAgent(userAgent).build())).build());
    }
    
    @GetMapping("/api/v1/users/username")
    public ResponseEntity<SuccessResponseBody> getUsername(HttpServletRequest httpRequest) {
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message(ms.getMessage("users.info.username", null, httpRequest.getLocale()))
                                                    .data("이름이름").build());
    }
    
    @PostMapping("/api/v1/users/refresh-token")
    public ResponseEntity<SuccessResponseBody> reissue(@RequestHeader("User-Agent") String userAgent,
                                                       @RequestBody ReissueTokensRequest request,
                                                       HttpServletRequest httpRequest) {
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message(ms.getMessage("users.login.jwt.renewed", null, httpRequest.getLocale()))
                                                    .data(reissueTokensHandler.handle(ReissueTokens.builder()
                                                                                                   .refreshToken(request.getRefreshToken())
                                                                                                   .userAgent(userAgent).build())).build());
    }
    
    @GetMapping("/api/v1/users/info")
    public ResponseEntity<SuccessResponseBody> getUserInfo(@AccessToken String accessToken,
                                                           HttpServletRequest httpRequest){
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                           .status(200)
                                                           .message(ms.getMessage("users.info", null, httpRequest.getLocale()))
                                                           .data(userService.handle(GetUserInfo.builder()
                                                                                               .userId(jwtManager.resolveUserId(accessToken))
                                                                                               .build())).build());
    }
    
    @PutMapping("/api/v1/users/nickname")
    public ResponseEntity<SuccessResponseBody> updateNickname(@AccessToken String accessToken,
                                                              @Valid @RequestBody UpdateNickNameRequest request,
                                                              HttpServletRequest httpRequest) {
        userService.handle(UpdateNickname.builder()
                                         .userId(jwtManager.resolveUserId(accessToken))
                                         .newNickname(request.getNewNickname()).build());
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                           .status(200)
                                                           .message(ms.getMessage("users.info.nickname.updated", null, httpRequest.getLocale()))
                                                           .data(null).build());
    }
}
