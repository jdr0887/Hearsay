package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@XmlRootElement(name = "simpleAminoAcidAllele")
@XmlType(name = "SimpleAminoAcidAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_amino_acid_allele")
public class SimpleAminoAcidAllele extends SimpleAllele {

    private static final long serialVersionUID = 6635059395827975767L;

    @Column(name = "change_type")
    @Enumerated(EnumType.STRING)
    private NucleotideChangeType aminoAcidChangeType;

    @XmlElementWrapper(name = "aminoAcidReferenceCoordinates")
    @XmlElement(name = "aminoAcidReferenceCoordinate")
    @ManyToMany(mappedBy = "simpleAminoAcidAlleles")
    private Set<AminoAcidReferenceCoordinate> aminoAcidReferenceCoordinates;

    public SimpleAminoAcidAllele() {
        super();
    }

    public NucleotideChangeType getAminoAcidChangeType() {
        return aminoAcidChangeType;
    }

    public void setAminoAcidChangeType(NucleotideChangeType aminoAcidChangeType) {
        this.aminoAcidChangeType = aminoAcidChangeType;
    }

    public Set<AminoAcidReferenceCoordinate> getAminoAcidReferenceCoordinates() {
        return aminoAcidReferenceCoordinates;
    }

    public void setAminoAcidReferenceCoordinates(Set<AminoAcidReferenceCoordinate> aminoAcidReferenceCoordinates) {
        this.aminoAcidReferenceCoordinates = aminoAcidReferenceCoordinates;
    }

    @Override
    public String toString() {
        return String.format("SimpleAminoAcidAllele [id=%s, allele=%s]", id, allele);
    }

}
