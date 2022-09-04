package com.biom.biombackend.users.features.userinfo;

import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import com.biom.biombackend.users.exceptions.ApplicationException;
import com.biom.biombackend.users.exceptions.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
                                          .orElseThrow(() -> new ApplicationException(ErrorType.UserNotFound, HttpStatus.NOT_FOUND));
        GetUserInfoResponse response = createGetUserInfoResponse(user);
        log.debug("created response: {}", response);
        return response;
    }
    
    @Override
    public void handle(UpdateNickname command) {
        log.debug("handling command: {}", command);
        BiomUser user = userRepository.findById(command.getUserId())
                                          .orElseThrow(() -> new ApplicationException(ErrorType.UserNotFound, HttpStatus.NOT_FOUND));
        user.setNickname(command.getNewNickname());
        userRepository.save(user);
    }
    
    private GetUserInfoResponse createGetUserInfoResponse(BiomUser user) {
        return GetUserInfoResponse.builder()
                                  .username(user.getUsername()).email(user.getEmail())
                                  .nickname(user.getNickname()).pictureUri(user.getPictureUri()).build();
    }
}
