package org.renci.hearsay.dao.model;

public enum StrandType {

    PLUS("+"),

    MINUS("-");

    private String value;

    private StrandType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
