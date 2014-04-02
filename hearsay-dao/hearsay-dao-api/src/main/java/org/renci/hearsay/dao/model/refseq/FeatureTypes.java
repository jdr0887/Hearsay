package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureTypes")
@XmlRootElement(name = "featureTypes")
@Entity
@Table(name = "feature_types")
public class FeatureTypes {

    @Column(name = "type_name", length = 31)
    private String typeName;

    public FeatureTypes() {
        super();
    }

}
