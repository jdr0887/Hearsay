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
@XmlRootElement(name = "simpleAminoAcidCanonicalAllele")
@XmlType(name = "SimpleAminoAcidCanonicalAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_amino_acid_canonical_allele")
public class SimpleAminoAcidCanonicalAllele extends SimpleCanonicalAllele {

    private static final long serialVersionUID = -8177693147276930063L;

    @XmlElementWrapper(name = "complexProteinCanonicalAlleles")
    @XmlElement(name = "complexProteinCanonicalAllele")
    @ManyToMany(targetEntity = ComplexProteinCanonicalAllele.class, cascade = { CascadeType.REMOVE,
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "simple_amino_acid_canonical_allele_complex_protein_canonical_allele", joinIndex = @Index(columnNames = { "simple_amino_acid_canonical_allele_fid" }))
    @JoinTable(name = "simple_amino_acid_canonical_allele_complex_protein_canonical_allele", joinColumns = @JoinColumn(name = "simple_amino_acid_canonical_allele_fid"), inverseJoinColumns = @JoinColumn(name = "complex_protein_canonical_allele_fid"))
    private Set<ComplexProteinCanonicalAllele> complexProteinCanonicalAlleles;

    public SimpleAminoAcidCanonicalAllele() {
        super();
    }

    public Set<ComplexProteinCanonicalAllele> getComplexProteinCanonicalAlleles() {
        return complexProteinCanonicalAlleles;
    }

    public void setComplexProteinCanonicalAlleles(Set<ComplexProteinCanonicalAllele> complexProteinCanonicalAlleles) {
        this.complexProteinCanonicalAlleles = complexProteinCanonicalAlleles;
    }

    @Override
    public String toString() {
        return String.format("SimpleAminoAcidCanonicalAllele [id=%s, active=%s]", id, active);
    }

}
