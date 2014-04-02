package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.renci.hearsay.dao.model.ref.GenomeRef;

@Entity
@Table(name = "var_set")
public class VarSet {

    @Column(name = "var_set_id")
    private Long varSetId;

    @Column(name = "ref_id")
    private GenomeRef genomeRef;

}
