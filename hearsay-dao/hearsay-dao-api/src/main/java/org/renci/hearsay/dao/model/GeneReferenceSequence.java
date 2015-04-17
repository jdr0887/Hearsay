package org.renci.hearsay.dao.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "geneReferenceSequence")
@XmlType(name = "GeneReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gene_reference_sequence")
@NamedQueries({ @NamedQuery(name = "GeneReferenceSequence.findAll", query = "SELECT a FROM GeneReferenceSequence a order by a.accession") })
public class GeneReferenceSequence extends GenomicReferenceSequence {

    private static final long serialVersionUID = -5045556711771008256L;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    public GeneReferenceSequence() {
        super();
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    @Override
    public String toString() {
        return String.format("GeneReferenceSequence [id=%s, identifier=%s]", id, identifier);
    }

}
