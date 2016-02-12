package org.renci.hearsay.dao.model;

public enum ContextualAlleleNameType {

    HGVS_GENOMIC("hgvs-genomic"),

    HGVS_MITO("hgvs-mito"),

    HGVS_CDNA("hgvs-cdna"),

    HGVS_PROTEIN_1("hgvs-protein-1"),

    HGVS_PROTEIN_3("hgvs-protein-3"),

    HGVS_RNA("hgvs-rna"),

    HGVS_NCRNA("hgvs-ncrna"),

    IVS("ivs"),

    CUSTOM("custom");

    private String value;

    private ContextualAlleleNameType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
