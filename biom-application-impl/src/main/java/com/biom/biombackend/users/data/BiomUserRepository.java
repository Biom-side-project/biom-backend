package com.biom.biombackend.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BiomUserRepository extends JpaRepository<BiomUser, Long> {
    BiomUser getByEmail(String email);
    BiomUser findBiomUserByEmail(String email);
}
