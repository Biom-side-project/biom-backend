package com.biom.biombackend.users.features.security;

import com.biom.biombackend.users.data.BiomUser;
import com.biom.biombackend.users.data.BiomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SecurityUserDetailsService implements UserDetailsService {

    private final BiomUserRepository biomUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BiomUser userInfo = biomUserRepository.findBiomUserByEmail(username);
        return new SecurityUser(userInfo);
    }
}
