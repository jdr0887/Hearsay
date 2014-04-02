package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transcr")
@XmlRootElement(name = "transcr")
@Entity
@Table(name = "transcr")
public class Transcr {

    @Column(name = "ver_id", length = 31)
    private String verId;

    @Column(name = "accession", length = 31)
    private String accession;

    @Column(name = "seq", length = 524287)
    private String seq;

    public Transcr() {
        super();
    }

}
