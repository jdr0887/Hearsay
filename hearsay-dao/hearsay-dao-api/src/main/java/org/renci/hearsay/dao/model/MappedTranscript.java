package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "mappedTranscript")
@XmlType(name = "MappedTranscript")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "mapped_transcript")
public class MappedTranscript extends BaseEntity {

    private static final long serialVersionUID = 362046030746960808L;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transcript_fid")
    private Transcript transcript;

    @ManyToOne
    @JoinColumn(name = "reference_sequence_fid")
    private ReferenceSequence referenceSequence;

    @Column(name = "genomic_start")
    private Integer genomicStart;

    @Column(name = "genomic_stop")
    private Integer genomicStop;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    @OneToMany(mappedBy = "mappedTranscript", fetch = FetchType.EAGER)
    @OrderBy("regionStart ASC")
    private Set<Region> regions;

    public MappedTranscript() {
        super();
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public ReferenceSequence getReferenceSequence() {
        return referenceSequence;
    }

    public void setReferenceSequence(ReferenceSequence referenceSequence) {
        this.referenceSequence = referenceSequence;
    }

    public Integer getGenomicStart() {
        return genomicStart;
    }

    public void setGenomicStart(Integer genomicStart) {
        this.genomicStart = genomicStart;
    }

    public Integer getGenomicStop() {
        return genomicStop;
    }

    public void setGenomicStop(Integer genomicStop) {
        this.genomicStop = genomicStop;
    }

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
    }

    public Set<Region> getRegions() {
        if (regions == null) {
            regions = new HashSet<Region>();
        }
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    @Override
    public String toString() {
        return String.format("MappedTranscript [genomicStart=%s, genomicStop=%s, strandType=%s, id=%s]", genomicStart,
                genomicStop, strandType, id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((genomicStart == null) ? 0 : genomicStart.hashCode());
        result = prime * result + ((genomicStop == null) ? 0 : genomicStop.hashCode());
        result = prime * result + ((strandType == null) ? 0 : strandType.hashCode());
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
        MappedTranscript other = (MappedTranscript) obj;
        if (genomicStart == null) {
            if (other.genomicStart != null)
                return false;
        } else if (!genomicStart.equals(other.genomicStart))
            return false;
        if (genomicStop == null) {
            if (other.genomicStop != null)
                return false;
        } else if (!genomicStop.equals(other.genomicStop))
            return false;
        if (strandType != other.strandType)
            return false;
        return true;
    }

}
