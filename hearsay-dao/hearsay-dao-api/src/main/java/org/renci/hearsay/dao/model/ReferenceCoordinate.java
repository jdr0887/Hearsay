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
@XmlRootElement(name = "referenceCoordinate")
@XmlType(name = "ReferenceCoordinate")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "reference_coordinate")
public class ReferenceCoordinate extends IdentifiableEntity {

    private static final long serialVersionUID = 608874481580966242L;

    @Column(name = "ref_allele")
    private String refAllele;

    @Column(name = "start")
    private Integer start;

    @Column(name = "end")
    private Integer end;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    @ManyToOne
    @JoinColumn(name = "intron_offset_fid")
    private IntronOffset intronOffset;

    @ManyToOne
    @JoinColumn(name = "reference_sequence_fid")
    private ReferenceSequence referenceSequence;

    @Column(name = "primary_transcript_region_type")
    @Enumerated(EnumType.STRING)
    private RegionType primaryTranscriptRegionType;

    @Column(name = "ancillary_transcript_region_type")
    @Enumerated(EnumType.STRING)
    private RegionType ancillaryTranscriptRegionType;

    public ReferenceCoordinate() {
        super();
    }

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
    }

    public String getRefAllele() {
        return refAllele;
    }

    public void setRefAllele(String refAllele) {
        this.refAllele = refAllele;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public IntronOffset getIntronOffset() {
        return intronOffset;
    }

    public void setIntronOffset(IntronOffset intronOffset) {
        this.intronOffset = intronOffset;
    }

    public ReferenceSequence getReferenceSequence() {
        return referenceSequence;
    }

    public void setReferenceSequence(ReferenceSequence referenceSequence) {
        this.referenceSequence = referenceSequence;
    }

    public RegionType getPrimaryTranscriptRegionType() {
        return primaryTranscriptRegionType;
    }

    public void setPrimaryTranscriptRegionType(RegionType primaryTranscriptRegionType) {
        this.primaryTranscriptRegionType = primaryTranscriptRegionType;
    }

    public RegionType getAncillaryTranscriptRegionType() {
        return ancillaryTranscriptRegionType;
    }

    public void setAncillaryTranscriptRegionType(RegionType ancillaryTranscriptRegionType) {
        this.ancillaryTranscriptRegionType = ancillaryTranscriptRegionType;
    }

    @Override
    public String toString() {
        return String.format("ReferenceCoordinate [id=%s, refAllele=%s, start=%s, end=%s]", id, refAllele, start, end);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((refAllele == null) ? 0 : refAllele.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
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
        ReferenceCoordinate other = (ReferenceCoordinate) obj;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (refAllele == null) {
            if (other.refAllele != null)
                return false;
        } else if (!refAllele.equals(other.refAllele))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        return true;
    }

}
