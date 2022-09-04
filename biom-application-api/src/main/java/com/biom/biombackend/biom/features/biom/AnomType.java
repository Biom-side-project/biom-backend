package com.biom.biombackend.biom.features.biom;

public enum AnomType {
    AnomedSuccessful(1),
    AlreadyAnomed(2);
    
    private final int anomCode;
    
    AnomType(int anomCode) {
        this.anomCode = anomCode;
    }
    
    public int getAnomCode() { return anomCode; }
}
