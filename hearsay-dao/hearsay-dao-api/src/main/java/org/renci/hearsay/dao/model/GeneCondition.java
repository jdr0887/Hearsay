package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "geneCondition")
@XmlType(name = "GeneCondition")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gene_condition")
public class GeneCondition extends BaseEntity {

    private static final long serialVersionUID = -1407404805678953995L;

    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @ManyToOne
    @JoinColumn(name = "resolved_condition_fid")
    private ResolvedCondition resolvedCondition;

    public GeneCondition() {
        super();
    }

}
