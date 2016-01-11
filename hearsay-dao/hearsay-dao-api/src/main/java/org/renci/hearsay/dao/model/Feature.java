package org.renci.hearsay.dao.model;

import java.util.HashSet;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@XmlType(propOrder = { "id", "note", "type", "locations" })
@XmlRootElement(name = "feature")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "feature")
@FetchGroups({ @FetchGroup(name = "includeAll", attributes = { @FetchAttribute(name = "locations"),
        @FetchAttribute(name = "referenceSequences") }) })
public class Feature implements Persistable {

    private static final long serialVersionUID = 1234926307644461359L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "feature_id_seq", sequenceName = "feature_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "type")
    private String type;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "note", length = 4096)
    private String note;

    @XmlElementWrapper(name = "locations")
    @XmlElement(name = "location")
    @ManyToMany(targetEntity = Location.class, fetch = FetchType.LAZY)
    @JoinTable(schema = "hearsay", name = "feature_location", joinColumns = @JoinColumn(name = "feature_fid") , inverseJoinColumns = @JoinColumn(name = "location_fid") , uniqueConstraints = {
            @UniqueConstraint(columnNames = { "feature_fid", "location_fid" }) })
    private Set<Location> locations;

    @XmlTransient
    @ManyToMany(targetEntity = ReferenceSequence.class, fetch = FetchType.LAZY)
    @JoinTable(schema = "hearsay", name = "reference_sequence_feature", joinColumns = @JoinColumn(name = "feature_fid") , inverseJoinColumns = @JoinColumn(name = "reference_sequence_fid") )
    private Set<ReferenceSequence> referenceSequences;

    public Feature() {
        super();
        this.referenceSequences = new HashSet<ReferenceSequence>();
        this.locations = new HashSet<Location>();
    }

    public Feature(String type) {
        this();
        this.type = type;
    }

    public Feature(String type, String note) {
        this();
        this.type = type;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<ReferenceSequence> getReferenceSequences() {
        return referenceSequences;
    }

    public void setReferenceSequences(Set<ReferenceSequence> referenceSequences) {
        this.referenceSequences = referenceSequences;
    }

    @Override
    public String toString() {
        return String.format("Feature [id=%s, type=%s, note=%s]", id, type, note);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Feature other = (Feature) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
