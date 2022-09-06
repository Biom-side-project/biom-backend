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
    
    @Override
    public String toString() {
        return "{\"ReportBiomResponse\":{" + "\"biomId\":" + ((biomId != null) ? ("\"" + biomId + "\"") : null) + ", \"type\":" + ((type != null) ? ("\"" + type + "\"") : null) + ", \"createdAt\":" + ((createdAt != null) ? ("\"" + createdAt + "\"") : null) + ", \"timeLeft\":" + timeLeft + "}}";
    }
}
