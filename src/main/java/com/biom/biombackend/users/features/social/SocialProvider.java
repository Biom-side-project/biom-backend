package com.biom.biombackend.users.features.social;

public enum SocialProvider {
    GOOGLE("GOOGLE"), NAVER("NAVER");
    
    private final String value;
    
    SocialProvider(String value) { this.value = value; }
    
    public String getValue() { return value; }
}
