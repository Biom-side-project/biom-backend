package com.biom.biombackend.users.web;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReissueTokensRequest {
    private String refreshToken;
}
