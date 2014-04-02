package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CdsEcNums")
@XmlRootElement(name = "cdsEcNums")
@Entity
@Table(name = "cds_ec_nums")
public class CdsEcNums {

    @Column(name = "refseq_cds_id")
    private Integer refseqCdsId;

    @Column(name = "ec_num", length = 31)
    private String ecNum;

    public CdsEcNums() {
        super();
    }

}
