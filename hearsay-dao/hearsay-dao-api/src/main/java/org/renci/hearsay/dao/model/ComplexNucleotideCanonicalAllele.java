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
@XmlRootElement(name = "complexNucleotideCanonicalAllele")
@XmlType(name = "ComplexNucleotideCanonicalAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "complex_nucleotide_canonical_allele")
public class ComplexNucleotideCanonicalAllele extends ComplexCanonicalAllele {

    private static final long serialVersionUID = 5587426611139169554L;

    @XmlElementWrapper(name = "simpleNucleotideCanonicalAlleles")
    @XmlElement(name = "simpleNucleotideCanonicalAllele")
    @ManyToMany(mappedBy = "complexNucleotideCanonicalAlleles")
    private Set<SimpleNucleotideCanonicalAllele> simpleNucleotideCanonicalAlleles;

    public ComplexNucleotideCanonicalAllele() {
        super();
    }

    public Set<SimpleNucleotideCanonicalAllele> getSimpleNucleotideCanonicalAlleles() {
        return simpleNucleotideCanonicalAlleles;
    }

    public void setSimpleNucleotideCanonicalAlleles(
            Set<SimpleNucleotideCanonicalAllele> simpleNucleotideCanonicalAlleles) {
        this.simpleNucleotideCanonicalAlleles = simpleNucleotideCanonicalAlleles;
    }

    @Override
    public String toString() {
        return String.format("ComplexNucleotideCanonicalAllele [id=%s, active=%s]", id, active);
    }

}
