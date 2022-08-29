package com.biom.biombackend.users.excepions;

public class RegionNotFoundException extends ExceptionWithStatusCode{
    
    private static final String regionNotFoundExceptionMessage = "지역정보를 찾을 수 없습니다.";
    
    public RegionNotFoundException() {
        super(regionNotFoundExceptionMessage, 404);
    }
}
