package com.biom.biombackend.users.features.social.google;

import com.biom.biombackend.users.features.social.JacksonOAuthAttributes;

public interface GoogleClient {
    
    JacksonOAuthAttributes getUserInfo(GetUserInfo command);
}
