package org.renci.hearsay.dao.model.omim;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Otype")
@XmlRootElement(name = "otype")
@Entity
@Table(name = "otype")
public class Otype {

    @Column(name = "type_key")
    private Integer typeKey;

    @Column(name = "symbol", length = 1)
    private String symbol;

    @Lob
    @Column(name = "type_name")
    private String typeName;

    public Otype() {
        super();
    }

}
