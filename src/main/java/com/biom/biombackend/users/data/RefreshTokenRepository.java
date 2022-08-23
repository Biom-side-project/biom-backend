package com.biom.biombackend.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

//    RefreshTokenEntity getReferenceByRefreshTokenValue(String refreshTokenValue);
    RefreshTokenEntity findByRefreshTokenValue(String refreshTokenValue);
}
