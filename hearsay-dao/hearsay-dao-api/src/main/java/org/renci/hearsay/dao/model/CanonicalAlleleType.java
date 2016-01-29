package org.renci.hearsay.dao.model;

public enum CanonicalAlleleType {

    NUCLEOTIDE("nucleotide"),

    AMINO_ACID("amino acid");

    private String value;

    private CanonicalAlleleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
