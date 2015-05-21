package org.renci.hearsay.dao.model;

public enum ReferenceSequenceType {

    CHROMOSOME("chromosome"),

    GENE("gene"),

    TRANSCRIPT("transcript"),

    AMINO_ACID("amino-acid");

    private String value;

    private ReferenceSequenceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
