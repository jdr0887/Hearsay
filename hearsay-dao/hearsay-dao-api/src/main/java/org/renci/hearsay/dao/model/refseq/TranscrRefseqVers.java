package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TranscrRefseqVers")
@XmlRootElement(name = "transcrRefseqVers")
@Entity
@Table(name = "transcr_refseq_vers")
public class TranscrRefseqVers {

    @Column(name = "ver_id", length = 31)
    private String verId;

    @Column(name = "refseq_ver")
    private String refseqVer;

    public TranscrRefseqVers() {
        super();
    }

}
