package com.biom.biombackend.users.data;

import com.biom.biombackend.users.features.social.SocialProvider;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class BiomUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    
    private String username;
    private String pictureUri;
    private String email;
    
    @Enumerated(value = EnumType.STRING)
    private SocialProvider socialType;
    
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
