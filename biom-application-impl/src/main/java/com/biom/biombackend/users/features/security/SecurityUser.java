package com.biom.biombackend.users.features.security;

import com.biom.biombackend.users.data.BiomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

class SecurityUser extends User {
    
    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    
    public SecurityUser(BiomUser biomUser) {
        super(biomUser.getEmail(),
              "",
              AuthorityUtils.createAuthorityList(biomUser.getRole().toString()));
    }
}
