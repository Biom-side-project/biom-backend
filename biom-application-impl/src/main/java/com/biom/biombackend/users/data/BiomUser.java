package com.biom.biombackend.users.data;

import com.biom.biombackend.users.features.social.SocialProvider;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BiomUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    
    private String username;
    private String pictureUri;
    private String email;
    private String nickname;
    
    @Enumerated(value = EnumType.STRING)
    private SocialProvider socialType;
    
    @Enumerated(value = EnumType.STRING)
    private Role role;
    
    @Override
    public String toString() {
        return "{\"BiomUser\":{" + "\"userId\":" + userId + ", \"username\":" + ((username != null) ? ("\"" + username + "\"") : null) + ", \"pictureUri\":" + ((pictureUri != null) ? ("\"" + pictureUri + "\"") : null) + ", \"email\":" + ((email != null) ? ("\"" + email + "\"") : null) + ", \"nickname\":" + ((nickname != null) ? ("\"" + nickname + "\"") : null) + ", \"socialType\":" + ((socialType != null) ? ("\"" + socialType + "\"") : null) + ", \"role\":" + ((role != null) ? ("\"" + role + "\"") : null) + "}}";
    }
}
