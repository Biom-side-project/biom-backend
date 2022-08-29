package com.biom.biombackend.biom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AnomRepository extends JpaRepository<Anom, UUID> {
    @Query("select count(anomId) " +
           "from Anom " +
           "where regionCode.regionCode =?1 and " +
           "createdAt between ?2 and ?3")
    long countByRegionCodeAndBetweenTimeInterval(long regionCode, LocalDateTime timeOne, LocalDateTime timeTwo);
}
