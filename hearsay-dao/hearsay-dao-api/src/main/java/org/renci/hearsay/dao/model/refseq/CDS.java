package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CDS")
@XmlRootElement(name = "cds")
@Entity
@Table(name = "cds")
public class CDS {

    @Column(name = "refseq_cds_id")
    private Long refseqCdsId;

    @Column(name = "refseq_ver")
    private String refseqVer;

    @Column(name = "protein_id", length = 31)
    private String proteinId;

    @Column(name = "product")
    private String product;

    @Column(name = "descr", length = 65535)
    private String descr;

    @Column(name = "transl", length = 65535)
    private String transl;

    @Column(name = "note", length = 1023)
    private String note;

    public CDS() {
        super();
    }

}
