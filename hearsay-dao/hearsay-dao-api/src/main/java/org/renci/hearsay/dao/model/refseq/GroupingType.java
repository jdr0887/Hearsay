package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupingType")
@XmlRootElement(name = "groupingType")
@Entity
@Table(name = "grouping_type")
public class GroupingType {

    @Column(name = "grouping_type_name", length = 15)
    private String groupingTypeName;

    public GroupingType() {
        super();
    }

}
