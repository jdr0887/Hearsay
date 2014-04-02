package org.renci.hearsay.dao.model.esp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SnpFreqPopulation")
@XmlRootElement(name = "snpFreqPopulation")
@Entity
@Table(name = "snp_freq_population")
public class SNPFreqPopulation {

    @Column(name = "loc_var_id")
    private Integer locVarId;

    @Column(name = "esp_version")
    private Integer espVersion;

    @Lob
    @Column(name = "population")
    private String population;

    @Column(name = "alt_allele_freq")
    private Float altAlleleFreq;

    @Column(name = "total_allele_count")
    private Integer totalAlleleCount;

    @Column(name = "alt_allele_count")
    private Integer altAlleleCount;

    public SNPFreqPopulation() {
        super();
    }

}
