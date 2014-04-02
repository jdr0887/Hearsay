package org.renci.hearsay.dao.model.omim;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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

    @Column(name = "omim_gene_id")
    private Integer omimGeneId;

    @Column(name = "version")
    private Date version;

    @Column(name = "mixed")
    private Boolean mixed;

    @Column(name = "otype")
    private Integer otype;

    @Lob
    @Column(name = "name")
    private String name;

    public Gene() {
        super();
    }

}
