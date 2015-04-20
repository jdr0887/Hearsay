package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "complexAminoAcidCanonicalAllele")
@XmlType(name = "ComplexAminoAcidCanonicalAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "complex_amino_acid_canonical_allele")
public class ComplexAminoAcidCanonicalAllele extends ComplexCanonicalAllele {

    private static final long serialVersionUID = -71630684553502226L;

    @XmlElementWrapper(name = "simpleAminoAcidCanonicalAlleles")
    @XmlElement(name = "simpleAminoAcidCanonicalAllele")
    @ManyToMany(mappedBy = "complexProteinCanonicalAlleles")
    private Set<SimpleAminoAcidCanonicalAllele> simpleAminoAcidCanonicalAlleles;

    public ComplexAminoAcidCanonicalAllele() {
        super();
    }

    public Set<SimpleAminoAcidCanonicalAllele> getSimpleAminoAcidCanonicalAlleles() {
        return simpleAminoAcidCanonicalAlleles;
    }

    public void setSimpleAminoAcidCanonicalAlleles(Set<SimpleAminoAcidCanonicalAllele> simpleAminoAcidCanonicalAlleles) {
        this.simpleAminoAcidCanonicalAlleles = simpleAminoAcidCanonicalAlleles;
    }

    @Override
    public String toString() {
        return String.format("ComplexProteinCanonicalAllele [id=%s, active=%s]", id, active);
    }

}
