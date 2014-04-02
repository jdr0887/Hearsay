package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CdsLocs")
@XmlRootElement(name = "cdsLocs")
@Entity
@Table(name = "cds_locs")
public class CdsLocs {

    @Column(name = "refseq_cds_id")
    private Integer refseqCdsId;

    @Column(name = "loc_region_group_id")
    private Integer locRegionGroupId;

    public CdsLocs() {
        super();
    }

}
