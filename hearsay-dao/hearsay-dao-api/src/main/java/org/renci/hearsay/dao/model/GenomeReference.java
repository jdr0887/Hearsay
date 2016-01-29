package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.FetchAttribute;
import org.apache.openjpa.persistence.FetchGroup;
import org.apache.openjpa.persistence.FetchGroups;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "genomeReference")
@XmlType(propOrder = { "name" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "genome_reference", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
@NamedQueries({ @NamedQuery(name = "GenomeReference.findAll", query = "SELECT a FROM GenomeReference a order by a.name"),
        @NamedQuery(name = "GenomeReference.findByName", query = "SELECT a FROM GenomeReference a where a.name = :name order by a.name") })
@FetchGroups({ @FetchGroup(name = "includeAll", attributes = { @FetchAttribute(name = "referenceSequences") }) })
public class GenomeReference extends IdentifiableEntity {

    private static final long serialVersionUID = -225305295285554428L;

    @XmlAttribute
    @Column(name = "name")
    private String name;

    @XmlTransient
    @OneToMany(mappedBy = "genomeReference")
    private Set<ReferenceSequence> referenceSequences;

    public GenomeReference() {
        super();
    }

    public GenomeReference(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ReferenceSequence> getReferenceSequences() {
        return referenceSequences;
    }

    public void setReferenceSequences(Set<ReferenceSequence> referenceSequences) {
        this.referenceSequences = referenceSequences;
    }

    @Override
    public String toString() {
        return String.format("GenomeReference [id=%s, name=%s]", id, name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        GenomeReference other = (GenomeReference) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
