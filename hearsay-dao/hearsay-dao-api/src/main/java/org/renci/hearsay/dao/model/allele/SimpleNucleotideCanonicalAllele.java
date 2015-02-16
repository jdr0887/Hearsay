package org.renci.hearsay.dao.model.allele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.jdbc.ContainerTable;
import org.apache.openjpa.persistence.jdbc.Index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "simpleNucleotideCanonicalAllele")
@XmlType(name = "SimpleNucleotideCanonicalAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_nucleotide_canonical_allele")
public class SimpleNucleotideCanonicalAllele extends SimpleCanonicalAllele {

    private static final long serialVersionUID = 2817111058839807582L;

    @XmlElementWrapper(name = "complexNucleotideCanonicalAlleles")
    @XmlElement(name = "complexNucleotideCanonicalAllele")
    @ManyToMany(targetEntity = ComplexNucleotideCanonicalAllele.class, cascade = { CascadeType.REMOVE,
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "simple_nucleotide_canonical_allele_complex_nucleotide_canonical_allele", joinIndex = @Index(columnNames = { "simple_nucleotide_canonical_allele_fid" }))
    @JoinTable(name = "simple_nucleotide_canonical_allele_complex_nucleotide_canonical_allele", joinColumns = @JoinColumn(name = "simple_nucleotide_canonical_allele_fid"), inverseJoinColumns = @JoinColumn(name = "complex_nucleotide_canonical_allele_fid"))
    private Set<ComplexNucleotideCanonicalAllele> complexNucleotideCanonicalAlleles;

    @XmlElementWrapper(name = "simpleNucleotideAlleles")
    @XmlElement(name = "simpleNucleotideAllele")
    @ManyToMany(mappedBy = "simpleNucleotideCanonicalAlleles")
    private Set<SimpleNucleotideAllele> simpleNucleotideAlleles;

    public SimpleNucleotideCanonicalAllele() {
        super();
    }

    public Set<ComplexNucleotideCanonicalAllele> getComplexNucleotideCanonicalAlleles() {
        return complexNucleotideCanonicalAlleles;
    }

    public void setComplexNucleotideCanonicalAlleles(
            Set<ComplexNucleotideCanonicalAllele> complexNucleotideCanonicalAlleles) {
        this.complexNucleotideCanonicalAlleles = complexNucleotideCanonicalAlleles;
    }

    public Set<SimpleNucleotideAllele> getSimpleNucleotideAlleles() {
        return simpleNucleotideAlleles;
    }

    public void setSimpleNucleotideAlleles(Set<SimpleNucleotideAllele> simpleNucleotideAlleles) {
        this.simpleNucleotideAlleles = simpleNucleotideAlleles;
    }

}
