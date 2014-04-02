package org.renci.hearsay.dao.model.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenomeRef")
@XmlRootElement(name = "genomeRef")
@Entity
@Table(name = "genome_ref")
public class GenomeRef {

    @Column(name = "ref_id")
    private Integer refId;

    @Column(name = "ref_source")
    private String refSource;

    @Column(name = "ref_ver")
    private String refVer;

    @Column(name = "ref_shortname", length = 50)
    private String refShortname;

    @Column(name = "basic_fasta_url", length = 1023)
    private String basicFastaUrl;

    @Column(name = "extras_fasta_url", length = 1023)
    private String extrasFastaUrl;

    public GenomeRef() {
        super();
    }

}
