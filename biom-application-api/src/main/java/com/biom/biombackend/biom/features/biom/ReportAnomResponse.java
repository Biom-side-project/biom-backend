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
    
    @Override
    public String toString() {
        return "{\"ReportAnomResponse\":{" + "\"anomId\":" + ((anomId != null) ? ("\"" + anomId + "\"") : null) + ", \"type\":" + ((type != null) ? ("\"" + type + "\"") : null) + ", \"createdAt\":" + ((createdAt != null) ? ("\"" + createdAt + "\"") : null) + ", \"timeLeft\":" + timeLeft + "}}";
    }
}
