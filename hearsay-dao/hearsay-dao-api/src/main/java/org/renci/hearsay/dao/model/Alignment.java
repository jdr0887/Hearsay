package org.renci.hearsay.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@XmlRootElement(name = "alignment")
@XmlType(name = "Alignment")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "alignment")
public class Alignment implements Persistable {

    private static final long serialVersionUID = 240726999951481482L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alignment_id_seq")
    @SequenceGenerator(name = "alignment_id_seq", sequenceName = "alignment_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reference_sequence_fid")
    private ReferenceSequence referenceSequence;

    @ManyToOne
    @JoinColumn(name = "genomic_location_fid")
    private Location genomicLocation;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    @OneToMany(mappedBy = "alignment", fetch = FetchType.EAGER)
    private List<Region> regions;

    @Column(name = "protein")
    private String protein;

    @ManyToOne
    @JoinColumn(name = "protein_location_fid")
    private Location proteinLocation;

    public Alignment() {
        super();
        this.regions = new ArrayList<Region>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReferenceSequence getReferenceSequence() {
        return referenceSequence;
    }

    public void setReferenceSequence(ReferenceSequence referenceSequence) {
        this.referenceSequence = referenceSequence;
    }

    public Location getGenomicLocation() {
        return genomicLocation;
    }

    public void setGenomicLocation(Location genomicLocation) {
        this.genomicLocation = genomicLocation;
    }

    public Location getProteinLocation() {
        return proteinLocation;
    }

    public void setProteinLocation(Location proteinLocation) {
        this.proteinLocation = proteinLocation;
    }

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return String.format("Alignment [id=%s, strandType=%s, protein=%s]", id, strandType, protein);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((protein == null) ? 0 : protein.hashCode());
        result = prime * result + ((strandType == null) ? 0 : strandType.hashCode());
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
        Alignment other = (Alignment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (protein == null) {
            if (other.protein != null)
                return false;
        } else if (!protein.equals(other.protein))
            return false;
        if (strandType != other.strandType)
            return false;
        return true;
    }

}
