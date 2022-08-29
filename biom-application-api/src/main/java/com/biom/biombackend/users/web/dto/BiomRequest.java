package com.biom.biombackend.users.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BiomRequest {
    private Long regionCode;
    
    @Override
    public String toString() {
        return "BiomRequest{" + "regionCode=" + regionCode + '}';
    }
}
