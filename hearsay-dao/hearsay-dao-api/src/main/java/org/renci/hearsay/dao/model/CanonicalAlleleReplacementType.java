package org.renci.hearsay.dao.model;

public enum CanonicalAlleleReplacementType {

    REPLACED_BY("replaced-by"),

    REPLACED_WITH("replaced-with");

    private String value;

    private CanonicalAlleleReplacementType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
