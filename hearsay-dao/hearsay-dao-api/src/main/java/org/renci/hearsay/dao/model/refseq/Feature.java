package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Feature")
@XmlRootElement(name = "feature")
@Entity
@Table(name = "feature")
public class Feature {

    @Column(name = "refseq_feature_id")
    private Long refseqFeatureId;

    @Column(name = "feature_type_type_name", length = 31)
    private String featureTypeTypeName;

    @Column(name = "refseq_ver")
    private String refseqVer;

    @Column(name = "note", length = 1023)
    private String note;

    @Column(name = "loc_region_group_id")
    private Integer locRegionGroupId;

    public Feature() {
        super();
    }

}
