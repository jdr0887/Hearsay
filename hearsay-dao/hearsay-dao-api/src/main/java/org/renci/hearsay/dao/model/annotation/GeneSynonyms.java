package org.renci.hearsay.dao.model.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneSynonyms")
@XmlRootElement(name = "geneSynonyms")
@Entity
@Table(name = "gene_synonyms")
public class GeneSynonyms {

    @Column(name = "gene_id")
    private Integer geneId;

    @Column(name = "synonym")
    private String synonym;

    public GeneSynonyms() {
        super();
    }

}
