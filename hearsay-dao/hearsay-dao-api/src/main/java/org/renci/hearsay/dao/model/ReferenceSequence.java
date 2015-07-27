package org.renci.hearsay.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "referenceSequence")
@XmlType(propOrder = { "type", "genomicLocation", "strandType", "gene", "genomeReference", "relationshipType" })
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

    @ManyToOne
    @JoinColumn(name = "genomic_location_fid")
    private Location genomicLocation;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @ManyToOne
    @JoinColumn(name = "genome_reference_fid")
    private GenomeReference genomeReference;

    @Column(name = "relationship_type")
    @Enumerated(EnumType.STRING)
    private ReferenceSequenceRelationshipType relationshipType;

    @XmlTransient
    @ManyToMany(targetEntity = Alignment.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "reference_sequence_alignment", joinColumns = @JoinColumn(name = "reference_sequence_fid"), inverseJoinColumns = @JoinColumn(name = "alignment_fid"))
    private List<Alignment> alignments;

    @XmlTransient
    @ManyToMany(targetEntity = Feature.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "reference_sequence_feature", joinColumns = @JoinColumn(name = "reference_sequence_fid"), inverseJoinColumns = @JoinColumn(name = "feature_fid"))
    private List<Feature> features;

    public ReferenceSequence() {
        super();
    }

    public ReferenceSequenceType getType() {
        return type;
    }

    public void setType(ReferenceSequenceType type) {
        this.type = type;
    }

    public Location getGenomicLocation() {
        return genomicLocation;
    }

    public void setGenomicLocation(Location genomicLocation) {
        this.genomicLocation = genomicLocation;
    }

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
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
        return String.format("ReferenceSequence [id=%s, type=%s]", id, type);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
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
        if (type != other.type)
            return false;
        return true;
    }

}
