package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.renci.hearsay.dao.model.ref.GenomeRefSeq;

@Entity
@Table(name = "asm_loc")
public class ASMLoc {

    @ManyToOne
    @JoinColumn(name = "asm_id")
    private ASM asm;

    @ManyToOne
    @JoinColumn(name = "ref_ver_accession")
    private GenomeRefSeq genomeRefSeq;

    @Column(name = "pos")
    private Integer pos;

    @Column(name = "homozygous")
    private Boolean homozygous;

    @Column(name = "genotype_qual")
    private Float genotypeQual;

}
