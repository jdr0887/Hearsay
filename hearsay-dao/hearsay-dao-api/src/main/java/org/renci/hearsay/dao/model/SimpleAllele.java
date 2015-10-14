package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "simpleAllele")
@XmlType(propOrder = { "allele", "name", "type", "referenceCoordinate", "molecularConsequences",
        "populationFrequencies" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "simple_allele")
public class SimpleAllele extends IdentifiableEntity {

    private static final long serialVersionUID = 608874481580966242L;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "canonical_allele_fid")
    private CanonicalAllele canonicalAllele;

    @XmlAttribute
    @Column(name = "allele")
    private String allele;

    @XmlAttribute
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @XmlAttribute
    private SimpleAlleleType type;

    @ManyToOne
    @JoinColumn(name = "reference_coordinate_fid")
    private ReferenceCoordinate referenceCoordinate;

    @OneToMany(mappedBy = "simpleAllele", fetch = FetchType.EAGER)
    private Set<MolecularConsequence> molecularConsequences;

    @OneToMany(mappedBy = "simpleAllele", fetch = FetchType.EAGER)
    private Set<PopulationFrequency> populationFrequencies;

    public SimpleAllele() {
        super();
        this.molecularConsequences = new HashSet<MolecularConsequence>();
        this.populationFrequencies = new HashSet<PopulationFrequency>();
    }

    public CanonicalAllele getCanonicalAllele() {
        return canonicalAllele;
    }

    public void setCanonicalAllele(CanonicalAllele canonicalAllele) {
        this.canonicalAllele = canonicalAllele;
    }

    public String getAllele() {
        return allele;
    }

    public void setAllele(String allele) {
        this.allele = allele;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleAlleleType getType() {
        return type;
    }

    public void setType(SimpleAlleleType type) {
        this.type = type;
    }

    public ReferenceCoordinate getReferenceCoordinate() {
        return referenceCoordinate;
    }

    public void setReferenceCoordinate(ReferenceCoordinate referenceCoordinate) {
        this.referenceCoordinate = referenceCoordinate;
    }

    public Set<MolecularConsequence> getMolecularConsequences() {
        return molecularConsequences;
    }

    public void setMolecularConsequences(Set<MolecularConsequence> molecularConsequences) {
        this.molecularConsequences = molecularConsequences;
    }

    public Set<PopulationFrequency> getPopulationFrequencies() {
        return populationFrequencies;
    }

    public void setPopulationFrequencies(Set<PopulationFrequency> populationFrequencies) {
        this.populationFrequencies = populationFrequencies;
    }

    @Override
    public String toString() {
        return String.format("SimpleAllele [id=%s, allele=%s, name=%s, type=%s]", id, allele, name, type);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((allele == null) ? 0 : allele.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((referenceCoordinate == null) ? 0 : referenceCoordinate.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        SimpleAllele other = (SimpleAllele) obj;
        if (allele == null) {
            if (other.allele != null)
                return false;
        } else if (!allele.equals(other.allele))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (referenceCoordinate == null) {
            if (other.referenceCoordinate != null)
                return false;
        } else if (!referenceCoordinate.equals(other.referenceCoordinate))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
