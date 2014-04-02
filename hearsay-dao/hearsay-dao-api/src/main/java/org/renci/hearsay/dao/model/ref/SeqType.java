package org.renci.hearsay.dao.model.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeqType")
@XmlRootElement(name = "seqType")
@Entity
@Table(name = "seq_type")
public class SeqType {

    @Column(name = "seq_type_name")
    private String seqTypeName;

    public SeqType() {
        super();
    }

}
