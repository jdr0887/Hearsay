package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionGroup")
@XmlRootElement(name = "regionGroup")
@Entity
@Table(name = "region_group")
public class RegionGroup {

    @Column(name = "region_group_id")
    private Long regionGroupId;

    @Column(name = "transcr_ver_id", length = 31)
    private String transcrVerId;

    @Column(name = "grouping_type", length = 15)
    private String groupingType;

    public RegionGroup() {
        super();
    }

}
