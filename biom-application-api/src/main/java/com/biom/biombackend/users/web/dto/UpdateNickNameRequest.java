package com.biom.biombackend.users.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.OverridesAttribute;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateNickNameRequest {
    @NotBlank(message = "{validation.notBlank.users.nickname}")
    private String newNickname;
    
    @Override
    public String toString() {
        return "{\"UpdateNickNameRequest\":{" + "\"newNickname\":" + ((newNickname != null) ? ("\"" + newNickname + "\"") : null) + "}}";
    }
}
