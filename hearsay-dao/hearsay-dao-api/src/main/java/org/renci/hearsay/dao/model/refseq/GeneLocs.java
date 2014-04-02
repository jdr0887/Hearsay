package org.renci.hearsay.dao.model.refseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneLocs")
@XmlRootElement(name = "geneLocs")
@Entity
@Table(name = "gene_locs")
public class GeneLocs {

    @Column(name = "refseq_gene_id")
    private Integer refseqGeneId;

    @Column(name = "loc_region_group_id")
    private Integer locRegionGroupId;

    public GeneLocs() {
        super();
    }

}
