package org.renci.hearsay.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "alignment")
@XmlType
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

    @XmlElementWrapper(name = "referenceSequences")
    @XmlElement(name = "referenceSequence")
    @ManyToMany(targetEntity = ReferenceSequence.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "reference_sequence_alignment", joinColumns = @JoinColumn(name = "alignment_fid"), inverseJoinColumns = @JoinColumn(name = "reference_sequence_fid"))
    private List<ReferenceSequence> referenceSequences;

    @OneToMany(mappedBy = "alignment", fetch = FetchType.EAGER)
    private List<Region> regions;

    @ManyToOne
    @JoinColumn(name = "protein_location_fid")
    private Location proteinLocation;

    @ManyToOne
    @JoinColumn(name = "cds_location_fid")
    private Location CDSLocation;

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

    public List<ReferenceSequence> getReferenceSequences() {
        return referenceSequences;
    }

    public void setReferenceSequences(List<ReferenceSequence> referenceSequences) {
        this.referenceSequences = referenceSequences;
    }

    public Location getCDSLocation() {
        return CDSLocation;
    }

    public void setCDSLocation(Location cDSLocation) {
        CDSLocation = cDSLocation;
    }

    public Location getProteinLocation() {
        return proteinLocation;
    }

    public void setProteinLocation(Location proteinLocation) {
        this.proteinLocation = proteinLocation;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public String toString() {
        return String.format("Alignment [id=%s]", id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        return true;
    }

}
