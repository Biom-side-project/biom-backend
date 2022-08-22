package com.biom.biombackend.users.features.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRefreshToken {
    private String username;
}
