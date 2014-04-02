package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionGroupRegions")
@XmlRootElement(name = "regionGroupRegions")
@Entity
@Table(name = "region_group_regions")
public class RegionGroupRegions {

    @Column(name = "region_group_id")
    private Integer regionGroupId;

    @Column(name = "region_start")
    private Integer regionStart;

    @Column(name = "region_end")
    private Integer regionEnd;

    public RegionGroupRegions() {
        super();
    }

}
