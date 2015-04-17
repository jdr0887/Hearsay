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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.jdbc.ContainerTable;
import org.apache.openjpa.persistence.jdbc.Index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "transcriptReferenceCoordinate")
@XmlType(name = "TranscriptReferenceCoordinate")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript_reference_coordinate")
public class TranscriptReferenceCoordinate extends ReferenceCoordinate {

    private static final long serialVersionUID = -9216702313390756017L;

    @Column(name = "region_type")
    @Enumerated(EnumType.STRING)
    private RegionType regionType;

    @XmlElementWrapper(name = "transcriptReferenceSequences")
    @XmlElement(name = "transcriptReferenceSequence")
    @ManyToMany(mappedBy = "transcriptReferenceCoordinates")
    private Set<TranscriptReferenceSequence> transcriptReferenceSequences;

    @XmlElementWrapper(name = "simpleTranscriptAlleles")
    @XmlElement(name = "simpleTranscriptAllele")
    @ManyToMany(targetEntity = TranscriptReferenceCoordinate.class, cascade = { CascadeType.REMOVE,
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "transcript_reference_coordinate_simple_transcript_allele", joinIndex = @Index(columnNames = { "transcript_reference_coordinate_fid" }))
    @JoinTable(name = "transcript_reference_coordinate_simple_transcript_allele", joinColumns = @JoinColumn(name = "transcript_reference_coordinate_fid"), inverseJoinColumns = @JoinColumn(name = "simple_transcript_allele_fid"))
    private Set<SimpleTranscriptAllele> simpleTranscriptAlleles;

    @OneToOne
    @JoinColumn(name = "intronic_coordinate_fid")
    private IntronicCoordinate intronicCoordinate;

    public TranscriptReferenceCoordinate() {
        super();
    }

    public RegionType getRegionType() {
        return regionType;
    }

    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public Set<TranscriptReferenceSequence> getTranscriptReferenceSequences() {
        return transcriptReferenceSequences;
    }

    public void setTranscriptReferenceSequences(Set<TranscriptReferenceSequence> transcriptReferenceSequences) {
        this.transcriptReferenceSequences = transcriptReferenceSequences;
    }

    public Set<SimpleTranscriptAllele> getSimpleTranscriptAlleles() {
        return simpleTranscriptAlleles;
    }

    public void setSimpleTranscriptAlleles(Set<SimpleTranscriptAllele> simpleTranscriptAlleles) {
        this.simpleTranscriptAlleles = simpleTranscriptAlleles;
    }

    public IntronicCoordinate getIntronicCoordinate() {
        return intronicCoordinate;
    }

    public void setIntronicCoordinate(IntronicCoordinate intronicCoordinate) {
        this.intronicCoordinate = intronicCoordinate;
    }

    @Override
    public String toString() {
        return String.format("TranscriptReferenceCoordinate [id=%s, refAllele=%s, start=%s, end=%s]", id, refAllele,
                start, end);
    }

}
