package com.biom.biombackend.biom.features.biom;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReportAnomResponse {
    private UUID anomId;
    private AnomType type;
    private LocalDateTime createdAt;
    private TimeLeft timeLeft;
}
