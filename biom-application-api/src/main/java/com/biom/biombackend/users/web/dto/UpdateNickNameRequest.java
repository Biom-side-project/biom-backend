package com.biom.biombackend.users.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateNickNameRequest {
    private String newNickname;
    
    @Override
    public String toString() {
        return "{\"UpdateNickNameRequest\":{" + "\"newNickname\":" + ((newNickname != null) ? ("\"" + newNickname + "\"") : null) + "}}";
    }
}
