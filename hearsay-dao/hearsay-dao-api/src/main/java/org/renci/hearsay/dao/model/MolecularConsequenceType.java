package org.renci.hearsay.dao.model;

public enum MolecularConsequenceType {

    TWO_THOUSAND_B_UPSTREAM_VARIANT("2KB upstream variant"),

    INTERGENIC_VARIANT("intergenic_variant"),

    SPLICE_SITE("Splice Site"),

    FRAMESHIFT_VARIANT("frameshift variant"),

    SYNONYMOUS_VARIANT("synonymous variant"),

    STOP_LOST("stop lost"),

    MISSENSE_VARIANT("missense variant"),

    NONSENSE("nonsense"),

    NON_CODING_TRANSCRIPT_VARIANT("non-coding transcript variant"),

    FIVE_HUNDRED_B_DOWNSTREAM_VARIANT("500B downstream variant"),

    SPLICE_DONOR_VARIANT("splice donor variant"),

    EXON_LOSS("exon_loss"),

    THREE_PRIME_UTR_VARIANT("3 prime UTR variant"),

    SPLICE_ACCEPTOR_VARIANT("splice acceptor variant"),

    INTRON_VARIANT("intron variant"),

    INFRAME_VARIANT("inframe_variant"),

    FIVE_PRIME_UTR_VARIANT("5 prime UTR variant"),

    REGULAR_REGION_ABLATION("regulatory region ablation");

    private String value;

    private MolecularConsequenceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
