package com.biom.biombackend.biom.features.biom;

public enum BiomType {
    BiomedSuccessful(1),
    AlreadyBiomed(2);
    
    private final int biomCode;
    
    BiomType(int biomCode) {
        this.biomCode = biomCode;
    }
    
    public int getBiomCode() { return biomCode; }
}
