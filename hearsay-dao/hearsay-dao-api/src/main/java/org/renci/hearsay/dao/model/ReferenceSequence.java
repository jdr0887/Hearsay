package org.renci.hearsay.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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

    @XmlAttribute
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ReferenceSequenceType type;

    @XmlAttribute
    @Column(name = "chromosome_type")
    @Enumerated(EnumType.STRING)
    private ReferenceSequenceChromosomeType chromosomeType;

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

    @OneToMany(mappedBy = "referenceSequence", fetch = FetchType.EAGER)
    private List<Alignment> alignments;

    @OneToMany(mappedBy = "referenceSequence", fetch = FetchType.EAGER)
    private List<Feature> features;

    public ReferenceSequence() {
        super();
        this.features = new ArrayList<Feature>();
        this.alignments = new ArrayList<Alignment>();
    }

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

    public List<Alignment> getAlignments() {
        return alignments;
    }

    public void setAlignments(List<Alignment> alignments) {
        this.alignments = alignments;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return String.format("ReferenceSequence [id=%s, type=%s, chromosomeType=%s, relationshipType=%s]", id, type,
                chromosomeType, relationshipType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((chromosomeType == null) ? 0 : chromosomeType.hashCode());
        result = prime * result + ((relationshipType == null) ? 0 : relationshipType.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReferenceSequence other = (ReferenceSequence) obj;
        if (chromosomeType != other.chromosomeType)
            return false;
        if (relationshipType != other.relationshipType)
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
