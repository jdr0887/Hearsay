package org.renci.hearsay.dao.model.allele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.jdbc.ContainerTable;
import org.apache.openjpa.persistence.jdbc.Index;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "transcriptReferenceSequence")
@XmlType(name = "TranscriptReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript_reference_sequence")
@NamedQueries({ @NamedQuery(name = "TranscriptReferenceSequence.findAll", query = "SELECT a FROM TranscriptReferenceSequence a order by a.accession") })
public class TranscriptReferenceSequence extends ReferenceSequence {

    private static final long serialVersionUID = 2602699552144041803L;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @Column(name = "coding_region_offset")
    private Integer codingRegionOffset;

    @XmlElementWrapper(name = "aminoAcidReferenceSequences")
    @XmlElement(name = "aminoAcidReferenceSequence")
    @ManyToMany(targetEntity = AminoAcidReferenceSequence.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "transcript_reference_sequence_amino_acid_reference_sequence", joinIndex = @Index(columnNames = { "transcript_reference_sequence_fid" }))
    @JoinTable(name = "transcript_reference_sequence_amino_acid_reference_sequence", joinColumns = @JoinColumn(name = "transcript_reference_sequence_fid"), inverseJoinColumns = @JoinColumn(name = "amino_acid_reference_sequence_fid"))
    private Set<AminoAcidReferenceSequence> aminoAcidReferenceSequences;

    @XmlElementWrapper(name = "transcriptReferenceCoordinates")
    @XmlElement(name = "transcriptReferenceCoordinate")
    @ManyToMany(targetEntity = TranscriptReferenceCoordinate.class, cascade = { CascadeType.REMOVE,
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "transcript_reference_sequence_transcript_reference_coordinate", joinIndex = @Index(columnNames = { "transcript_reference_sequence_fid" }))
    @JoinTable(name = "transcript_reference_sequence_transcript_reference_coordinate", joinColumns = @JoinColumn(name = "transcript_reference_sequence_fid"), inverseJoinColumns = @JoinColumn(name = "transcript_reference_coordinate_fid"))
    private Set<TranscriptReferenceCoordinate> transcriptReferenceCoordinates;

    public TranscriptReferenceSequence() {
        super();
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public Integer getCodingRegionOffset() {
        return codingRegionOffset;
    }

    public void setCodingRegionOffset(Integer codingRegionOffset) {
        this.codingRegionOffset = codingRegionOffset;
    }

    public Set<AminoAcidReferenceSequence> getAminoAcidReferenceSequences() {
        return aminoAcidReferenceSequences;
    }

    public void setAminoAcidReferenceSequences(Set<AminoAcidReferenceSequence> aminoAcidReferenceSequences) {
        this.aminoAcidReferenceSequences = aminoAcidReferenceSequences;
    }

    public Set<TranscriptReferenceCoordinate> getTranscriptReferenceCoordinates() {
        return transcriptReferenceCoordinates;
    }

    public void setTranscriptReferenceCoordinates(Set<TranscriptReferenceCoordinate> transcriptReferenceCoordinates) {
        this.transcriptReferenceCoordinates = transcriptReferenceCoordinates;
    }

    @Override
    public String toString() {
        return String.format("TranscriptReferenceSequence [id=%s, identifier=%s, codingRegionOffset=%s]", id,
                identifier, codingRegionOffset);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((codingRegionOffset == null) ? 0 : codingRegionOffset.hashCode());
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
        TranscriptReferenceSequence other = (TranscriptReferenceSequence) obj;
        if (codingRegionOffset == null) {
            if (other.codingRegionOffset != null)
                return false;
        } else if (!codingRegionOffset.equals(other.codingRegionOffset))
            return false;
        return true;
    }

}
