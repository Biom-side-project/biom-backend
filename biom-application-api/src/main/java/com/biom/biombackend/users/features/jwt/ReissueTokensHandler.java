package com.biom.biombackend.users.features.jwt;

public interface ReissueTokensHandler {
    
    ReissueTokensResponse handle(ReissueTokens command);
}
