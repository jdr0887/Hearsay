package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureQualifiers")
@XmlRootElement(name = "featureQualifiers")
@Entity
@Table(name = "feature_qualifiers")
public class FeatureQualifiers {

    @Column(name = "refseq_feature_id")
    private Integer refseqFeatureId;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    public FeatureQualifiers() {
        super();
    }

}
