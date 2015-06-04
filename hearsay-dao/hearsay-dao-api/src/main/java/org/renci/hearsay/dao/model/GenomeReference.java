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

import org.apache.openjpa.persistence.jdbc.Index;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "genomeReference")
@XmlType(name = "GenomeReference")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genome_reference")
@NamedQueries({ @NamedQuery(name = "GenomeReference.findAll", query = "SELECT a FROM GenomeReference a order by a.name") })
public class GenomeReference implements Persistable {

    private static final long serialVersionUID = -225305295285554428L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genome_reference_id_seq")
    @SequenceGenerator(name = "genome_reference_id_seq", sequenceName = "genome_reference_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Index
    @Column(name = "name")
    private String name;

    @JsonProperty("identifiers")
    @XmlElementWrapper(name = "identifiers")
    @XmlElement(name = "identifier")
    @ManyToMany(targetEntity = Identifier.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(name = "genome_reference_identifier", joinColumns = @JoinColumn(name = "genome_reference_fid"), inverseJoinColumns = @JoinColumn(name = "identifier_fid"))
    private List<Identifier> identifiers;

    @XmlTransient
    @OneToMany(mappedBy = "genomeReference")
    private List<ReferenceSequence> referenceSequences;

    public GenomeReference() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Identifier> getIdentifiers() {
        if (identifiers == null) {
            identifiers = new ArrayList<Identifier>();
        }
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReferenceSequence> getReferenceSequences() {
        return referenceSequences;
    }

    public void setReferenceSequences(List<ReferenceSequence> referenceSequences) {
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
