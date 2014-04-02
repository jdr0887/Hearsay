package org.renci.hearsay.dao.model.genome1k;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SnpFreqSubpopulation")
@XmlRootElement(name = "snpFreqSubpopulation")
@Entity
@Table(name = "snp_freq_subpopulation")
public class SNPFreqSubpopulation {

    @Column(name = "loc_var_id")
    private Integer locVarId;

    @Column(name = "gen1000_version")
    private Integer gen1000Version;

    @Column(name = "population", length = 5)
    private String population;

    @Column(name = "alt_allele_freq")
    private Float altAlleleFreq;

    @Column(name = "total_allele_count")
    private Integer totalAlleleCount;

    @Column(name = "alt_allele_count")
    private Integer altAlleleCount;

    public SNPFreqSubpopulation() {
        super();
    }

}
