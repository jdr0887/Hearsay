package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocType")
@XmlRootElement(name = "locType")
@Entity
@Table(name = "loc_type")
public class LocType {

    @Lob
    @Column(name = "loc_type")
    private String locType;

    public LocType() {
        super();
    }

}
