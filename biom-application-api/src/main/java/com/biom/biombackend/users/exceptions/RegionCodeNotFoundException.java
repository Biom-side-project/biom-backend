package com.biom.biombackend.users.exceptions;

public class RegionCodeNotFoundException extends ExceptionWithStatusCode{
    
    private static final String regionCodeNotFoundExceptionMessage = "존재하지 않는 지역 코드 입니다.";
    
    public RegionCodeNotFoundException() {
        super(regionCodeNotFoundExceptionMessage, 404);
    }
}
