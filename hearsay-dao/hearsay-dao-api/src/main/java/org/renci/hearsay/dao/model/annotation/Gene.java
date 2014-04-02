package org.renci.hearsay.dao.model.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gene")
@XmlRootElement(name = "gene")
@Entity
@Table(name = "gene")
public class Gene {

    @Column(name = "gene_id")
    private Long geneId;

    @Column(name = "preferred_name")
    private String preferredName;

    @Column(name = "preferred_descr", length = 4095)
    private String preferredDescr;

    public Gene() {
        super();
    }

}
