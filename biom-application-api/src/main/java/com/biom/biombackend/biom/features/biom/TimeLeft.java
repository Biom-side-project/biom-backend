package com.biom.biombackend.biom.features.biom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
public class TimeLeft {
    private ChronoUnit timeUnit;
    private long amount;
}
