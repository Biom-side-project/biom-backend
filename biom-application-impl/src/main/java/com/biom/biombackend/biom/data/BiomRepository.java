package com.biom.biombackend.biom.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BiomRepository extends JpaRepository<Biom, UUID> {
}