package org.renci.hearsay.dao.model;

public enum CanonicalAlleleType {

    NUCLEOTIDE("nucleotide"),

    AMINO_ACID("amino-acid");

    private String name;

    private CanonicalAlleleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
