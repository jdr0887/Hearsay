package org.renci.hearsay.dao.model.allele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@XmlRootElement(name = "simpleNucleotideAllele")
@XmlType(name = "SimpleNucleotideAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_nucleotide_allele")
public class SimpleNucleotideAllele extends SimpleAllele {

    private static final long serialVersionUID = -3948698741794560257L;

    @Column(name = "change_type")
    @Enumerated(EnumType.STRING)
    private NucleotideChangeType nucleotideChangeType;

    @XmlElementWrapper(name = "simpleNucleotideCanonicalAlleles")
    @XmlElement(name = "simpleNucleotideCanonicalAllele")
    @ManyToMany(targetEntity = TranscriptReferenceCoordinate.class, cascade = { CascadeType.REMOVE,
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "simple_nucleotide_allele_simple_nucleotide_canonical_allele", joinIndex = @Index(columnNames = { "simple_nucleotide_allele_fid" }))
    @JoinTable(name = "simple_nucleotide_allele_simple_nucleotide_canonical_allele", joinColumns = @JoinColumn(name = "simple_nucleotide_allele_fid"), inverseJoinColumns = @JoinColumn(name = "simple_nucleotide_canonical_allele_fid"))
    private Set<SimpleNucleotideCanonicalAllele> simpleNucleotideCanonicalAlleles;

    public SimpleNucleotideAllele() {
        super();
    }

    public NucleotideChangeType getNucleotideChangeType() {
        return nucleotideChangeType;
    }

    public void setNucleotideChangeType(NucleotideChangeType nucleotideChangeType) {
        this.nucleotideChangeType = nucleotideChangeType;
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
        return String.format("SimpleNucleotideAllele [id=%s, allele=%s]", id, allele);
    }

}
