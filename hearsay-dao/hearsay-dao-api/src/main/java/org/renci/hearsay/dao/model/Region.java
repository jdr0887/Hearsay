package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "region")
@XmlType(name = "Region")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "region")
public class Region implements Persistable {

    private static final long serialVersionUID = -1132524849060275751L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_id_seq")
    @SequenceGenerator(name = "region_id_seq", sequenceName = "region_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transcript_alignment_fid")
    private TranscriptAlignment transcriptAlignment;

    @Column(name = "region_type")
    @Enumerated(EnumType.STRING)
    private RegionType regionType;

    @Column(name = "region_start")
    private Integer regionStart;

    @Column(name = "region_stop")
    private Integer regionStop;

    @Column(name = "cds_start")
    private Integer cdsStart;

    @Column(name = "cds_stop")
    private Integer cdsStop;

    @Column(name = "transcript_start")
    private Integer transcriptStart;

    @Column(name = "transcript_stop")
    private Integer transcriptStop;

    public Region() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TranscriptAlignment getTranscriptAlignment() {
        return transcriptAlignment;
    }

    public void setTranscriptAlignment(TranscriptAlignment transcriptAlignment) {
        this.transcriptAlignment = transcriptAlignment;
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

    public Integer getRegionStop() {
        return regionStop;
    }

    public void setRegionStop(Integer regionStop) {
        this.regionStop = regionStop;
    }

    public Integer getCdsStart() {
        return cdsStart;
    }

    public void setCdsStart(Integer cdsStart) {
        this.cdsStart = cdsStart;
    }

    public Integer getCdsStop() {
        return cdsStop;
    }

    public void setCdsStop(Integer cdsStop) {
        this.cdsStop = cdsStop;
    }

    public Integer getTranscriptStart() {
        return transcriptStart;
    }

    public void setTranscriptStart(Integer transcriptStart) {
        this.transcriptStart = transcriptStart;
    }

    public Integer getTranscriptStop() {
        return transcriptStop;
    }

    public void setTranscriptStop(Integer transcriptStop) {
        this.transcriptStop = transcriptStop;
    }

    @Override
    public String toString() {
        return String
                .format("Region [id=%s, regionType=%s, regionStart=%s, regionStop=%s, transcriptStart=%s, transcriptStop=%s, cdsStart=%s, cdsStop=%s]",
                        id, regionType, regionStart, regionStop, transcriptStart, transcriptStop, cdsStart, cdsStop);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdsStart == null) ? 0 : cdsStart.hashCode());
        result = prime * result + ((cdsStop == null) ? 0 : cdsStop.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((regionStart == null) ? 0 : regionStart.hashCode());
        result = prime * result + ((regionStop == null) ? 0 : regionStop.hashCode());
        result = prime * result + ((regionType == null) ? 0 : regionType.hashCode());
        result = prime * result + ((transcriptStart == null) ? 0 : transcriptStart.hashCode());
        result = prime * result + ((transcriptStop == null) ? 0 : transcriptStop.hashCode());
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
        Region other = (Region) obj;
        if (cdsStart == null) {
            if (other.cdsStart != null)
                return false;
        } else if (!cdsStart.equals(other.cdsStart))
            return false;
        if (cdsStop == null) {
            if (other.cdsStop != null)
                return false;
        } else if (!cdsStop.equals(other.cdsStop))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (regionStart == null) {
            if (other.regionStart != null)
                return false;
        } else if (!regionStart.equals(other.regionStart))
            return false;
        if (regionStop == null) {
            if (other.regionStop != null)
                return false;
        } else if (!regionStop.equals(other.regionStop))
            return false;
        if (regionType != other.regionType)
            return false;
        if (transcriptStart == null) {
            if (other.transcriptStart != null)
                return false;
        } else if (!transcriptStart.equals(other.transcriptStart))
            return false;
        if (transcriptStop == null) {
            if (other.transcriptStop != null)
                return false;
        } else if (!transcriptStop.equals(other.transcriptStop))
            return false;
        return true;
    }

}
