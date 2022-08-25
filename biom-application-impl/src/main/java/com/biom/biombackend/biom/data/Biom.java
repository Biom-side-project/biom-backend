package com.biom.biombackend.biom.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Biom extends BiomBaseEntity {
    @Id
    private UUID biomId;
    private String userId;
    
    private String sidoCode;
    private String sigunguCode;
    private String eupmyeondongCode;
}
