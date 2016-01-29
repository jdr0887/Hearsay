package org.renci.hearsay.dao.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "alignment")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "alignment")
@NamedQueries({ @NamedQuery(name = "Alignment.findAll", query = "SELECT a FROM Alignment a") })
@FetchGroups({
        @FetchGroup(name = "includeManyToOnes", attributes = { @FetchAttribute(name = "proteinLocation"),
                @FetchAttribute(name = "CDSLocation") }),
        @FetchGroup(name = "includeRegions", fetchGroups = { "includeManyToOnes" }, attributes = { @FetchAttribute(name = "regions") }),
        @FetchGroup(name = "includeAll", fetchGroups = { "includeRegions" }, attributes = {
                @FetchAttribute(name = "referenceSequences"), }) })
public class Alignment implements Persistable {

    private static final long serialVersionUID = 240726999951481482L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alignment_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "alignment_id_seq", sequenceName = "alignment_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @XmlTransient
    @ManyToMany(targetEntity = ReferenceSequence.class, fetch = FetchType.LAZY)
    @JoinTable(schema = "hearsay", name = "reference_sequence_alignment", joinColumns = @JoinColumn(name = "alignment_fid") , inverseJoinColumns = @JoinColumn(name = "reference_sequence_fid") )
    private Set<ReferenceSequence> referenceSequences;

    @XmlElementWrapper(name = "regions")
    @XmlElement(name = "region")
    @OneToMany(mappedBy = "alignment")
    private List<Region> regions;

    @ManyToOne
    @JoinColumn(name = "protein_location_fid")
    private Location proteinLocation;

    @ManyToOne
    @JoinColumn(name = "cds_location_fid")
    private Location CDSLocation;

    public Alignment() {
        super();
        this.referenceSequences = new HashSet<ReferenceSequence>();
        this.regions = new ArrayList<Region>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ReferenceSequence> getReferenceSequences() {
        return referenceSequences;
    }

    public void setReferenceSequences(Set<ReferenceSequence> referenceSequences) {
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
