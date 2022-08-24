package com.biom.biombackend.users.features.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateRefreshToken {
    private String email;
}
