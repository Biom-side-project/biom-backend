package com.biom.biombackend.users.features.userinfo;

import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import com.biom.biombackend.users.excepions.ExceptionWithStatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class DefaultUserInfoService implements UserService{
    
    private final BiomUserRepository userRepository;
    
    
    @Override
    public GetUserInfoResponse handle(GetUserInfo command) {
        log.debug("handling command: {}", command);
        Long userId = command.getUserId();
        BiomUser user = userRepository.findById(userId)
                                          .orElseThrow(() -> new ExceptionWithStatusCode("존재하지 않는 유저입니다.", 404));
        GetUserInfoResponse response = createGetUserInfoResponse(user);
        log.debug("created response: {}", response);
        return response;
    }
    
    private GetUserInfoResponse createGetUserInfoResponse(BiomUser user) {
        return GetUserInfoResponse.builder()
                                  .username(user.getUsername()).email(user.getEmail())
                                  .nickname(user.getNickname()).pictureUri(user.getPictureUri()).build();
    }
}
