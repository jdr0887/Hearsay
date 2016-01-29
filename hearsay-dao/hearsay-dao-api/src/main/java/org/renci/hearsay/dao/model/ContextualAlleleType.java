package org.renci.hearsay.dao.model;

/**
 * 
 * @author jdr0887
 */
public enum ContextualAlleleType {

    GENOMIC("HGVS, genomic, top level"),

    TRANSCRIPT("HGVS, coding, RefSeq"),

    AMINO_ACID("HGVS, protein, RefSeq");

    private String type;

    private ContextualAlleleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
