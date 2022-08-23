package com.biom.biombackend.users.web;

import com.biom.biombackend.users.features.login.SocialLoginRequest;
import com.biom.biombackend.users.features.login.SocialLoginService;
import com.biom.biombackend.users.web.dto.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final SocialLoginService socialLoginService;

    @PostMapping("/api/v1/login/naver")
    public ResponseEntity<SuccessResponseBody> loginNaver(@RequestBody SocialLoginRequest request){
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message("로그인에 성공하였습니다.")
                                                    .data(socialLoginService.loginNaver(request)).build());
    }
    @PostMapping("/api/v1/login/google")
    public ResponseEntity<SuccessResponseBody> loginGoogle(@RequestBody SocialLoginRequest request) {
        return ResponseEntity.ok(SuccessResponseBody.builder().status(200).message("로그인에 성공하였습니다.")
                                                    .data(socialLoginService.loginGoogle(request)).build());
    }
    
    @GetMapping("/api/v1/users/username")
    public ResponseEntity<SuccessResponseBody> getUsername() {
        return ResponseEntity.ok(SuccessResponseBody.builder()
                                                    .status(200)
                                                    .message("유저의 이름을 반환합니다.")
                                                    .data("이름이름").build());
    }
}
