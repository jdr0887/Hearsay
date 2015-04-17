package org.renci.hearsay.dao.model;

/**
 * got these definitions from http://ghr.nlm.nih.gov/handbook/mutationsanddisorders/possiblemutations
 * 
 * @author jdr0887
 */
public enum NucleotideChangeType {

    MISSENSE_MUTATION(
            "This type of mutation is a change in one DNA base pair that results in the substitution of one amino acid for another in the protein made by a gene."),

    NONSENSE_MUTATION(
            "A nonsense mutation is also a change in one DNA base pair. Instead of substituting one amino acid for another, however, the altered DNA sequence prematurely signals the cell to stop building a protein. This type of mutation results in a shortened protein that may function improperly or not at all."),

    INSERTION(
            "An insertion changes the number of DNA bases in a gene by adding a piece of DNA. As a result, the protein made by the gene may not function properly."),

    DELETION(
            "A deletion changes the number of DNA bases by removing a piece of DNA. Small deletions may remove one or a few base pairs within a gene, while larger deletions can remove an entire gene or several neighboring genes. The deleted DNA may alter the function of the resulting protein(s)."),

    DUPLICATION(
            "A duplication consists of a piece of DNA that is abnormally copied one or more times. This type of mutation may alter the function of the resulting protein."),

    FRAMESHIFT_MUTATION(
            "This type of mutation occurs when the addition or loss of DNA bases changes a gene’s reading frame. A reading frame consists of groups of 3 bases that each code for one amino acid. A frameshift mutation shifts the grouping of these bases and changes the code for amino acids. The resulting protein is usually nonfunctional. Insertions, deletions, and duplications can all be frameshift mutations."),

    REPEAT_EXPANSION(
            "Nucleotide repeats are short DNA sequences that are repeated a number of times in a row. For example, a trinucleotide repeat is made up of 3-base-pair sequences, and a tetranucleotide repeat is made up of 4-base-pair sequences. A repeat expansion is a mutation that increases the number of times that the short DNA sequence is repeated. This type of mutation can cause the resulting protein to function improperly.");

    private String description;

    private NucleotideChangeType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
