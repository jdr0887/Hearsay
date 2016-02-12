package org.renci.hearsay.dao.model;

/**
 * 
 * @author jdr0887
 */
public enum ContextualAlleleType {

    GENOMIC("genomic"),

    TRANSCRIPT("transcript"),

    AMINO_ACID("amino acid");

    private String value;

    private ContextualAlleleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
