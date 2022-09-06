package com.biom.biombackend.biom.data;

import com.biom.biombackend.users.data.BiomUser;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class Biom extends BiomBaseEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID biomId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private BiomUser user;
    
    @ManyToOne
    @JoinColumn(name = "region_code", nullable = false)
    private KoreaRegionCode regionCode;
    
    @Override
    public String toString() {
        return "[{\"Biom\":{" + "\"biomId\":" + ((biomId != null) ? ("\"" + biomId + "\"") : null) + ", \"user\":" + user + ", \"regionCode\":" + regionCode + "}}, " + super.toString() + "]";
    }
}
