package org.renci.hearsay.dao.model;

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
@XmlRootElement(name = "aminoAcidReferenceCoordinate")
@XmlType(name = "AminoAcidReferenceCoordinate")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "amino_acid_reference_coordinate")
public class AminoAcidReferenceCoordinate extends ReferenceCoordinate {

    private static final long serialVersionUID = 2566062559532243242L;

    @XmlElementWrapper(name = "aminoAcidReferenceSequences")
    @XmlElement(name = "aminoAcidReferenceSequence")
    @ManyToMany(mappedBy = "aminoAcidReferenceCoordinates")
    private Set<AminoAcidReferenceSequence> aminoAcidReferenceSequences;

    @XmlElementWrapper(name = "simpleAminoAcidAlleles")
    @XmlElement(name = "simpleAminoAcidAllele")
    @ManyToMany(targetEntity = SimpleAminoAcidAllele.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "amino_acid_reference_coordinate_simple_amino_acid_allele", joinIndex = @Index(columnNames = { "amino_acid_reference_coordinate_fid" }))
    @JoinTable(name = "amino_acid_reference_coordinate_simple_amino_acid_allele", joinColumns = @JoinColumn(name = "amino_acid_reference_coordinate_fid"), inverseJoinColumns = @JoinColumn(name = "simple_transcript_allele_fid"))
    private Set<SimpleAminoAcidAllele> simpleAminoAcidAlleles;

    public AminoAcidReferenceCoordinate() {
        super();
    }

    public Set<AminoAcidReferenceSequence> getAminoAcidReferenceSequences() {
        return aminoAcidReferenceSequences;
    }

    public void setAminoAcidReferenceSequences(Set<AminoAcidReferenceSequence> aminoAcidReferenceSequences) {
        this.aminoAcidReferenceSequences = aminoAcidReferenceSequences;
    }

    public Set<SimpleAminoAcidAllele> getSimpleAminoAcidAlleles() {
        return simpleAminoAcidAlleles;
    }

    public void setSimpleAminoAcidAlleles(Set<SimpleAminoAcidAllele> simpleAminoAcidAlleles) {
        this.simpleAminoAcidAlleles = simpleAminoAcidAlleles;
    }
    
}
