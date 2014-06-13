package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@XmlRootElement(name = "transcriptInterval")
@XmlType(name = "TranscriptInterval")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript_interval")
public class MappedTranscript extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "transcript_fid")
    private Transcript transcript;

    @ManyToOne
    @JoinColumn(name = "reference_sequence_fid")
    private ReferenceSequence referenceSequence;

    @Column(name = "region_type")
    @Enumerated(EnumType.STRING)
    private RegionType regionType;

    @Column(name = "region_end")
    private Integer regionStart;

    @Column(name = "region_end")
    private Integer regionEnd;

    @Column(name = "cds_end")
    private Integer cdsStart;

    @Column(name = "cds_end")
    private Integer cdsEnd;

    @Column(name = "transcript_end")
    private Integer transcriptStart;

    @Column(name = "transcript_end")
    private Integer transcriptEnd;

    public MappedTranscript() {
        super();
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public RegionType getRegionType() {
        return regionType;
    }

    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public Integer getRegionStart() {
        return regionStart;
    }

    public void setRegionStart(Integer regionStart) {
        this.regionStart = regionStart;
    }

    public Integer getRegionEnd() {
        return regionEnd;
    }

    public void setRegionEnd(Integer regionEnd) {
        this.regionEnd = regionEnd;
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

    public Integer getTranscriptStart() {
        return transcriptStart;
    }

    public void setTranscriptStart(Integer transcriptStart) {
        this.transcriptStart = transcriptStart;
    }

    public Integer getTranscriptEnd() {
        return transcriptEnd;
    }

    public void setTranscriptEnd(Integer transcriptEnd) {
        this.transcriptEnd = transcriptEnd;
    }

    @Override
    public String toString() {
        return String
                .format("TranscriptExon [transcript=%s, regionType=%s, regionStart=%s, regionEnd=%s, cdsStart=%s, cdsEnd=%s, transcriptStart=%s, transcriptEnd=%s]",
                        transcript, regionType, regionStart, regionEnd, cdsStart, cdsEnd, transcriptStart,
                        transcriptEnd);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cdsEnd == null) ? 0 : cdsEnd.hashCode());
        result = prime * result + ((cdsStart == null) ? 0 : cdsStart.hashCode());
        result = prime * result + ((regionEnd == null) ? 0 : regionEnd.hashCode());
        result = prime * result + ((regionStart == null) ? 0 : regionStart.hashCode());
        result = prime * result + ((regionType == null) ? 0 : regionType.hashCode());
        result = prime * result + ((transcript == null) ? 0 : transcript.hashCode());
        result = prime * result + ((transcriptEnd == null) ? 0 : transcriptEnd.hashCode());
        result = prime * result + ((transcriptStart == null) ? 0 : transcriptStart.hashCode());
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
        if (regionEnd == null) {
            if (other.regionEnd != null)
                return false;
        } else if (!regionEnd.equals(other.regionEnd))
            return false;
        if (regionStart == null) {
            if (other.regionStart != null)
                return false;
        } else if (!regionStart.equals(other.regionStart))
            return false;
        if (regionType != other.regionType)
            return false;
        if (transcript == null) {
            if (other.transcript != null)
                return false;
        } else if (!transcript.equals(other.transcript))
            return false;
        if (transcriptEnd == null) {
            if (other.transcriptEnd != null)
                return false;
        } else if (!transcriptEnd.equals(other.transcriptEnd))
            return false;
        if (transcriptStart == null) {
            if (other.transcriptStart != null)
                return false;
        } else if (!transcriptStart.equals(other.transcriptStart))
            return false;
        return true;
    }

}
