package org.renci.hearsay.dao.model;

/**
 * got these definitions from http://ghr.nlm.nih.gov/handbook/mutationsanddisorders/possiblemutations
 * 
 * @author jdr0887
 */
public enum MutationChangeType {

    DELETION(
            "Genetic material is removed or deleted. A few bases can be deleted (as shown on the left) or it can be complete or partial loss of a chromosome (shown on right)."),

    FRAMESHIFT(
            "The insertion or deletion of a number of bases that is not a multiple of 3. This alters the reading frame of the gene and frequently results in a premature stop codon and protein truncation."),

    INSERTION(
            "When genetic material is put into another region of DNA. This may be the insertion of 1 or more bases, or it can be part of one chromosome being inserted into another, non-homologous chromosome."),

    MISSENSE(
            "A change in DNA sequence that changes the codon to a different amino acid. Not all missense mutations are deleterious, some changes can have no effect. Because of the ambiguity of missense mutations, it is often difficult to interpret the consequences of these mutations in causing disease."),

    NONSENSE(
            "A change in the genetic code that results in the coding for a stop codon rather than an amino acid. The shortened protein is generally non-function or its function is impeded."),

    POINT("A single base change in DNA sequence. A point mutation may be silent, missense, or nonsense."),

    SILENT(
            "A change in the genetic sequence that does not change the protein sequence. This can occur because of redundancy in the genetic code where an amino acid may be encoded for by multiple codons. "),

    SPLICE_SITE(
            "A change in the genetic sequence that occurs at the boundary of the exons and introns. The consensus sequences at these boundaries signal where to cut out introns and rejoin exons in the mRNA. A change in these sequences can eliminate splicing at that site which would change the reading frame and protein sequence."),

    TRANSLOCATION(
            "A structural abnormality of chromosomes where genetic material is exchanged between two or more non-homologous chromosomes.");

    private String description;

    private MutationChangeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
