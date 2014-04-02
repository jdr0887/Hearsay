package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "asm_loc")
public class ASMLocVar {

    @ManyToOne
    @JoinColumn(name = "asm_id")
    private ASM asm;

    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocVar locVar;

    @Column(name = "homozygous")
    private Boolean homozygous;

    @Column(name = "genotype_qual")
    private Float genotypeQual;

}
