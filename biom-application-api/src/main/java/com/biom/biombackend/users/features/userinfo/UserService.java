package com.biom.biombackend.users.features.userinfo;

public interface UserService {
    GetUserInfoResponse handle(GetUserInfo command);
}
