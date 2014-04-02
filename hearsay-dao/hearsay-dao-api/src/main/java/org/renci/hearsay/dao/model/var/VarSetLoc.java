package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.renci.hearsay.dao.model.ref.GenomeRefSeq;

@Entity
@Table(name = "var_set")
public class VarSetLoc {

    @ManyToOne
    @JoinColumn(name = "var_set_id")
    private Long varSet;

    @ManyToOne
    @JoinColumn(name = "ref_ver_accession")
    private GenomeRefSeq genomeRefSeq;

    @Column(name = "pos")
    private Integer pos;

    @Column(name = "vcffilter")
    private String vcfFilter;

    @Column(name = "qual")
    private Float qual;

}
