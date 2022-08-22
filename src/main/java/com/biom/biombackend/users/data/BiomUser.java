package com.biom.biombackend.users.data;

import com.biom.biombackend.users.features.social.SocialProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BiomUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    
    private String username;
    private String pictureUri;
    private String email;
    
    @Enumerated(value = EnumType.STRING)
    private SocialProvider socialType;
    
    private Role role;
}
