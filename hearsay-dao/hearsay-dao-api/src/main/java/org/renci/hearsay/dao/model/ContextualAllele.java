package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "contextualAllele")
@XmlType(propOrder = { "allele", "name", "type", "referenceCoordinate", "molecularConsequences", "populationFrequencies" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "contextual_allele")
@FetchGroups({
        @FetchGroup(name = "includeManyToOnes", attributes = { @FetchAttribute(name = "canonicalAllele"),
                @FetchAttribute(name = "referenceCoordinate") }),
        @FetchGroup(name = "includeAll", fetchGroups = { "includeManyToOnes" }, attributes = {
                @FetchAttribute(name = "molecularConsequences"), @FetchAttribute(name = "populationFrequencies") }) })
public class ContextualAllele extends IdentifiableEntity {

    private static final long serialVersionUID = 608874481580966242L;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "canonical_allele_fid")
    private CanonicalAllele canonicalAllele;

    @XmlAttribute
    @Column(name = "allele")
    private String allele;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @XmlAttribute
    private ContextualAlleleType type;

    @ManyToOne
    @JoinColumn(name = "reference_coordinate_fid")
    private ReferenceCoordinate referenceCoordinate;

    @XmlElementWrapper(name = "alleleNames")
    @XmlElement(name = "name")
    @ManyToMany(targetEntity = ContextualAlleleName.class)
    @JoinTable(schema = "hearsay", name = "contextual_allele_contextual_allele_name", joinColumns = @JoinColumn(name = "contextual_allele_fid") , inverseJoinColumns = @JoinColumn(name = "contextual_allele_name_fid") , uniqueConstraints = {
            @UniqueConstraint(columnNames = { "contextual_allele_fid", "contextual_allele_name_fid" }) })
    private Set<ContextualAlleleName> alleleNames;

    @OneToMany(mappedBy = "contextualAllele")
    private Set<MolecularConsequence> molecularConsequences;

    @OneToMany(mappedBy = "contextualAllele")
    private Set<PopulationFrequency> populationFrequencies;

    public ContextualAllele() {
        super();
        this.alleleNames = new HashSet<ContextualAlleleName>();
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

    public Set<ContextualAlleleName> getAlleleNames() {
        return alleleNames;
    }

    public void setAlleleNames(Set<ContextualAlleleName> alleleNames) {
        this.alleleNames = alleleNames;
    }

    public ContextualAlleleType getType() {
        return type;
    }

    public void setType(ContextualAlleleType type) {
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
        return String.format("ContextualAllele [id=%s, allele=%s, type=%s]", id, allele, type);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((allele == null) ? 0 : allele.hashCode());
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
        ContextualAllele other = (ContextualAllele) obj;
        if (allele == null) {
            if (other.allele != null)
                return false;
        } else if (!allele.equals(other.allele))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}
