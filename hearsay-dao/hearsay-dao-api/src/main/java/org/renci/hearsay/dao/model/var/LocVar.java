package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.renci.hearsay.dao.model.ref.GenomeRef;
import org.renci.hearsay.dao.model.ref.GenomeRefSeq;

@Entity
@Table(name = "loc_var")
public class LocVar {

    @Column(name = "loc_var_id")
    private Long locVarId;

    @ManyToOne
    @JoinColumn(name = "ref_id")
    private GenomeRef genomeRef;

    @ManyToOne
    @JoinColumn(name = "ref_ver_accession")
    private GenomeRefSeq referenceVersionAccession;

    @Column(name = "pos")
    private Integer pos;

    @Lob
    @Column(name = "ref")
    private String ref;

    @Lob
    @Column(name = "alt")
    private String alt;

    @ManyToOne
    @JoinColumn(name = "type")
    private VarType type;

    public LocVar() {
        super();
    }

}
