package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CdsTranslExceptions")
@XmlRootElement(name = "cdsTranslExceptions")
@Entity
@Table(name = "cds_transl_exceptions")
public class CdsTranslExceptions {

    @Column(name = "refseq_cds_id")
    private Integer refseqCdsId;

    @Column(name = "start_loc")
    private Integer startLoc;

    @Column(name = "stop_loc")
    private Integer stopLoc;

    @Column(name = "amino_acid", length = 31)
    private String aminoAcid;

    public CdsTranslExceptions() {
        super();
    }

}
