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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "region")
@XmlType(propOrder = { "id", "regionType", "regionLocation", "cdsLocation", "transcriptLocation" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "region")
public class Region implements Persistable {

    private static final long serialVersionUID = -4018421194871513651L;

    @XmlAttribute
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_id_seq")
    @SequenceGenerator(name = "region_id_seq", sequenceName = "region_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "alignment_fid")
    private Alignment alignment;

    @XmlAttribute
    @Column(name = "region_type")
    @Enumerated(EnumType.STRING)
    private RegionType regionType;

    @ManyToOne
    @JoinColumn(name = "region_location_fid")
    private Location regionLocation;

    @ManyToOne
    @JoinColumn(name = "cds_location_fid")
    private Location cdsLocation;

    @ManyToOne
    @JoinColumn(name = "transcript_location_fid")
    private Location transcriptLocation;

    public Region() {
        super();
    }

    public Region(RegionType regionType) {
        super();
        this.regionType = regionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public RegionType getRegionType() {
        return regionType;
    }

    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public Location getRegionLocation() {
        return regionLocation;
    }

    public void setRegionLocation(Location regionLocation) {
        this.regionLocation = regionLocation;
    }

    public Location getCdsLocation() {
        return cdsLocation;
    }

    public void setCdsLocation(Location cdsLocation) {
        this.cdsLocation = cdsLocation;
    }

    public Location getTranscriptLocation() {
        return transcriptLocation;
    }

    public void setTranscriptLocation(Location transcriptLocation) {
        this.transcriptLocation = transcriptLocation;
    }

    @Override
    public String toString() {
        return String.format("Region [id=%s, regionType=%s]", id, regionType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((regionType == null) ? 0 : regionType.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (regionType != other.regionType)
            return false;
        return true;
    }

}
