package com.biom.biombackend.users.web;

import com.biom.biombackend.users.features.jwt.ReissueTokens;
import com.biom.biombackend.users.features.jwt.ReissueTokensHandler;
import com.biom.biombackend.users.features.login.ProcessLogin;
import com.biom.biombackend.users.features.login.SocialLoginRequest;
import com.biom.biombackend.users.features.login.SocialLoginService;
import com.biom.biombackend.users.web.dto.ReissueTokensRequest;
import com.biom.biombackend.users.web.dto.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final SocialLoginService socialLoginService;
    private final ReissueTokensHandler reissueTokensHandler;

    @PostMapping("/api/v1/login/naver")
    public ResponseEntity<SuccessResponseBody> loginNaver(@RequestBody SocialLoginRequest request){
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message("로그인에 성공하였습니다.")
                                                    .data(socialLoginService.loginNaver(request)).build());
    }
    @PostMapping("/api/v1/login/google")
    public ResponseEntity<SuccessResponseBody> loginGoogle(@RequestHeader("User-Agent") String userAgent,
                                                           @RequestBody SocialLoginRequest request) {
        return ResponseEntity.ok(SuccessResponseBody.builder().status(200).message("로그인에 성공하였습니다.")
                                                    .data(socialLoginService.loginGoogle(ProcessLogin.builder()
                                                                                                     .accessToken(request.getAccessToken())
                                                                                                     .userAgent(userAgent).build())).build());
    }
    
    @GetMapping("/api/v1/users/username")
    public ResponseEntity<SuccessResponseBody> getUsername() {
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message("유저의 이름을 반환합니다.")
                                                    .data("이름이름").build());
    }
    
    @PostMapping("/api/v1/users/refresh-token")
    public ResponseEntity<SuccessResponseBody> reissue(@RequestHeader("User-Agent") String userAgent,
                                                       @RequestBody ReissueTokensRequest request) {
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message("새로운 토큰들을 반환합니다.")
                                                    .data(reissueTokensHandler.handle(ReissueTokens.builder()
                                                                                                   .refreshToken(request.getRefreshToken())
                                                                                                   .userAgent(userAgent).build())).build());
    }
}