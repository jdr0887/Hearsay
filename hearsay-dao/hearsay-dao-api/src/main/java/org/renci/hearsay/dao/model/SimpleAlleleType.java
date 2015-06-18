package org.renci.hearsay.dao.model;

public enum SimpleAlleleType {

    GENOMIC("HGVS, genomic, top level"),

    TRANSCRIPT("HGVS, coding, RefSeq"),

    AMINO_ACID("HGVS, protein, RefSeq");

    private String type;

    private SimpleAlleleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
