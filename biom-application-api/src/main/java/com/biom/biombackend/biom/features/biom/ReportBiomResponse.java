package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReportBiomResponse {
    private UUID biomId;
    private BiomType type;
    private LocalDateTime createdAt;
    private TimeLeft timeLeft;
}
