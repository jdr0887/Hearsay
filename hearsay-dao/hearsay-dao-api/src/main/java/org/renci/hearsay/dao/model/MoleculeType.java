package org.renci.hearsay.dao.model;

import java.util.Arrays;
import java.util.List;

/**
 * http://www.ncbi.nlm.nih.gov/books/NBK21091/table/ch18.T.refseq_accession_numbers_and_mole/?report=objectonly
 * 
 * @author jdr0887
 */
public enum MoleculeType {

    GENOMIC("nucleotide", Arrays.asList("AC_", "NC_", "NG_", "NT_", "NW_", "NS_", "NZ_")),

    PROTEIN("amino-acid", Arrays.asList("AP_", "NP_", "YP_", "XP_", "ZP_")),

    RNA("RNA", Arrays.asList("NR_", "XR_")),

    mRNA("mRNA", Arrays.asList("NM_", "XM_"));

    private String name;

    private List<String> prefixes;

    private MoleculeType(String name, List<String> prefixes) {
        this.name = name;
        this.prefixes = prefixes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(List<String> prefixes) {
        this.prefixes = prefixes;
    }

}
