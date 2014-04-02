package org.renci.hearsay.dao.model.var;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ASMLocVarQC {

    @ManyToOne
    @JoinColumn(name = "asm_id")
    private ASM asm;

    @ManyToOne
    @JoinColumn(name = "loc_var_id")
    private LocVar locVar;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "qd")
    private Float qd;

    @Column(name = "read_pos_rank_sum")
    private float readPosRankSum;

    @Column(name = "frac_reads_with_dels")
    private Float fracReadsWithDels;

    @Column(name = "hrun")
    private Integer hrun;

    @Column(name = "strand_score")
    private Float strandScore;

    @Column(name = "ref_depth")
    private Integer refDepth;

    @Column(name = "alt_depth")
    private Integer altDepth;

}
