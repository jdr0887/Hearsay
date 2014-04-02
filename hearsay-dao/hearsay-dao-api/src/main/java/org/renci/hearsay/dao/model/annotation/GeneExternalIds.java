package org.renci.hearsay.dao.model.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneExternalIds")
@XmlRootElement(name = "geneExternalIds")
@Entity
@Table(name = "gene_external_ids")
public class GeneExternalIds {

    @Column(name = "gene_id")
    private Integer geneId;

    @Column(name = "namespace", length = 31)
    private String namespace;

    @Column(name = "namespace_ver", length = 31)
    private String namespaceVer;

    @Column(name = "gene_external_id")
    private Integer geneExternalId;

    public GeneExternalIds() {
        super();
    }

}
