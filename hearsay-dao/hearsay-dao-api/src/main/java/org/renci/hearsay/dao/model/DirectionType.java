package org.renci.hearsay.dao.model;

public enum DirectionType {

    PLUS("+"),

    MINUS("-");

    private String value;

    private DirectionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
