package com.biom.biombackend.users.web.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AnomRequest {
    private Long regionCode;
    
    @Override
    public String toString() {
        return "AnomRequest{" + "regionCode=" + regionCode + '}';
    }
}
