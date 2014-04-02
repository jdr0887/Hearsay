package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gene")
@XmlRootElement(name = "gene")
@Entity
@Table(name = "gene")
public class Gene {

    @Column(name = "refseq_gene_id")
    private Long refseqGeneId;

    @Column(name = "refseq_ver")
    private String refseqVer;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "descr", length = 4095)
    private String descr;

    public Gene() {
        super();
    }

}
