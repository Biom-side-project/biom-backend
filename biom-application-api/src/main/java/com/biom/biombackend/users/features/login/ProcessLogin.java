package com.biom.biombackend.users.features.login;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class ProcessLogin {
    private String accessToken;
    private String userAgent;
    
    @Override
    public String toString() {
        return "{\"ProcessLogin\":{" + "" + ((accessToken != null) ? ("\"accessToken\":\"" + accessToken + "\"") : ("\"accessToken\":" + null)) + "," + ((userAgent != null) ? ("\"userAgent\":\"" + userAgent + "\"") : ("\"userAgent\":" + null)) + "}}";
    }
}
