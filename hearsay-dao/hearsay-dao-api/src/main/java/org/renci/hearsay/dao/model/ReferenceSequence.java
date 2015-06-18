package org.renci.hearsay.dao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "referenceSequence")
@XmlType(name = "ReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "reference_sequence")
@NamedQueries({ @NamedQuery(name = "ReferenceSequence.findAll", query = "SELECT a FROM ReferenceSequence a") })
public class ReferenceSequence extends IdentifiableEntity {

    private static final long serialVersionUID = -488057011816731553L;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ReferenceSequenceType type;

    @Column(name = "chromosome_type")
    @Enumerated(EnumType.STRING)
    private ReferenceSequenceChromosomeType chromosomeType;

    @Column(name = "cds_start")
    private Integer cdsStart;

    @Column(name = "cds_end")
    private Integer cdsEnd;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @ManyToOne
    @JoinColumn(name = "genome_reference_fid")
    private GenomeReference genomeReference;

    @Column(name = "relationship_type")
    @Enumerated(EnumType.STRING)
    private ReferenceSequenceRelationshipType relationshipType;

    @ManyToOne
    @JoinColumn(name = "parent_fid")
    private ReferenceSequence parent;

    @OneToMany(mappedBy = "parent")
    private List<ReferenceSequence> relatedChildren;

    public ReferenceSequenceType getType() {
        return type;
    }

    public void setType(ReferenceSequenceType type) {
        this.type = type;
    }

    public ReferenceSequenceChromosomeType getChromosomeType() {
        return chromosomeType;
    }

    public void setChromosomeType(ReferenceSequenceChromosomeType chromosomeType) {
        this.chromosomeType = chromosomeType;
    }

    public Integer getCdsStart() {
        return cdsStart;
    }

    public void setCdsStart(Integer cdsStart) {
        this.cdsStart = cdsStart;
    }

    public Integer getCdsEnd() {
        return cdsEnd;
    }

    public void setCdsEnd(Integer cdsEnd) {
        this.cdsEnd = cdsEnd;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public GenomeReference getGenomeReference() {
        return genomeReference;
    }

    public void setGenomeReference(GenomeReference genomeReference) {
        this.genomeReference = genomeReference;
    }

    public ReferenceSequenceRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(ReferenceSequenceRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public ReferenceSequence getParent() {
        return parent;
    }

    public void setParent(ReferenceSequence parent) {
        this.parent = parent;
    }

    public List<ReferenceSequence> getRelatedChildren() {
        return relatedChildren;
    }

    public void setRelatedChildren(List<ReferenceSequence> relatedChildren) {
        this.relatedChildren = relatedChildren;
    }

    @Override
    public String toString() {
        return String.format(
                "ReferenceSequence [id=%s, type=%s, chromosomeType=%s, cdsStart=%s, cdsEnd=%s, relationshipType=%s]",
                id, type, chromosomeType, cdsStart, cdsEnd, relationshipType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdsEnd == null) ? 0 : cdsEnd.hashCode());
        result = prime * result + ((cdsStart == null) ? 0 : cdsStart.hashCode());
        result = prime * result + ((chromosomeType == null) ? 0 : chromosomeType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((relationshipType == null) ? 0 : relationshipType.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReferenceSequence other = (ReferenceSequence) obj;
        if (cdsEnd == null) {
            if (other.cdsEnd != null)
                return false;
        } else if (!cdsEnd.equals(other.cdsEnd))
            return false;
        if (cdsStart == null) {
            if (other.cdsStart != null)
                return false;
        } else if (!cdsStart.equals(other.cdsStart))
            return false;
        if (chromosomeType != other.chromosomeType)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (relationshipType != other.relationshipType)
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
