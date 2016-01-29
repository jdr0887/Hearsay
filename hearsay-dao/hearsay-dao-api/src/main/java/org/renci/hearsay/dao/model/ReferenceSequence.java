package org.renci.hearsay.dao.model;

import java.util.Set;

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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "referenceSequence")
@XmlType(propOrder = { "type", "genomicLocation", "strandType", "gene", "genomeReference", "relationshipType" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "reference_sequence")
@NamedQueries({ @NamedQuery(name = "ReferenceSequence.findAll", query = "SELECT a FROM ReferenceSequence a") })
@FetchGroups({
        @FetchGroup(name = "includeManyToOnes", attributes = { @FetchAttribute(name = "genomicLocation"), @FetchAttribute(name = "gene"),
                @FetchAttribute(name = "genomeReference") }),
        @FetchGroup(name = "includeAlignments", fetchGroups = { "includeManyToOnes" }, attributes = {
                @FetchAttribute(name = "alignments") }),
        @FetchGroup(name = "includeFeatures", fetchGroups = { "includeManyToOnes" }, attributes = { @FetchAttribute(name = "features") }),
        @FetchGroup(name = "includeAll", fetchGroups = { "includeManyToOnes" }, attributes = { @FetchAttribute(name = "alignments"),
                @FetchAttribute(name = "features") }) })
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
    @ManyToMany(targetEntity = Alignment.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(schema = "hearsay", name = "reference_sequence_alignment", joinColumns = @JoinColumn(name = "reference_sequence_fid") , inverseJoinColumns = @JoinColumn(name = "alignment_fid") , uniqueConstraints = {
            @UniqueConstraint(columnNames = { "reference_sequence_fid", "alignment_fid" }) })
    private Set<Alignment> alignments;

    @XmlTransient
    @ManyToMany(targetEntity = Feature.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(schema = "hearsay", name = "reference_sequence_feature", joinColumns = @JoinColumn(name = "reference_sequence_fid") , inverseJoinColumns = @JoinColumn(name = "feature_fid") , uniqueConstraints = {
            @UniqueConstraint(columnNames = { "reference_sequence_fid", "feature_fid" }) })
    private Set<Feature> features;

    public ReferenceSequence() {
        super();
    }

    public ReferenceSequence(ReferenceSequenceType type) {
        super();
        this.type = type;
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

    public Set<Alignment> getAlignments() {
        return alignments;
    }

    public void setAlignments(Set<Alignment> alignments) {
        this.alignments = alignments;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
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
