package com.biom.biombackend.biom.data;

import com.biom.biombackend.users.data.BiomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface BiomRepository extends JpaRepository<Biom, UUID> {
    
    @Query("select count(biomId) " +
           "from Biom " +
           "where regionCode.regionCode =?1 and " +
           "createdAt between ?2 and ?3")
    long countByRegionCodeAndBetweenTimeInterval(long regionCode, LocalDateTime timeOne, LocalDateTime timeTwo);
    
    boolean existsByUserAndRegionCodeAndCreatedAtBetween(BiomUser user, KoreaRegionCode regionCode, LocalDateTime timeOne, LocalDateTime timeTwo);
    Optional<Biom> findByUserAndRegionCodeAndCreatedAtBetween(BiomUser user, KoreaRegionCode regionCode, LocalDateTime timeOne, LocalDateTime timeTwo);
}
