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

    @ManyToOne
    @JoinColumn(name = "location_fid")
    private Location location;

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

    public String getRefAllele() {
        return refAllele;
    }

    public void setRefAllele(String refAllele) {
        this.refAllele = refAllele;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
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
        return String
                .format("ReferenceCoordinate [id=%s, refAllele=%s, strandType=%s, primaryTranscriptRegionType=%s, ancillaryTranscriptRegionType=%s]",
                        id, refAllele, strandType, primaryTranscriptRegionType, ancillaryTranscriptRegionType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((ancillaryTranscriptRegionType == null) ? 0 : ancillaryTranscriptRegionType.hashCode());
        result = prime * result + ((primaryTranscriptRegionType == null) ? 0 : primaryTranscriptRegionType.hashCode());
        result = prime * result + ((refAllele == null) ? 0 : refAllele.hashCode());
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
        ReferenceCoordinate other = (ReferenceCoordinate) obj;
        if (ancillaryTranscriptRegionType != other.ancillaryTranscriptRegionType)
            return false;
        if (primaryTranscriptRegionType != other.primaryTranscriptRegionType)
            return false;
        if (refAllele == null) {
            if (other.refAllele != null)
                return false;
        } else if (!refAllele.equals(other.refAllele))
            return false;
        if (strandType != other.strandType)
            return false;
        return true;
    }

}
