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
@XmlRootElement(name = "aminoAcidReferenceSequence")
@XmlType(name = "AminoAcidReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "amino_acid_reference_sequence")
public class AminoAcidReferenceSequence extends ReferenceSequence {

    private static final long serialVersionUID = -881228321137040835L;

    @XmlElementWrapper(name = "aminoAcidReferenceCoordinates")
    @XmlElement(name = "aminoAcidReferenceCoordinate")
    @ManyToMany(targetEntity = AminoAcidReferenceCoordinate.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "amino_acid_reference_sequence_amino_acid_reference_coordinate", joinIndex = @Index(columnNames = { "amino_acid_reference_sequence_fid" }))
    @JoinTable(name = "amino_acid_reference_sequence_amino_acid_reference_coordinate", joinColumns = @JoinColumn(name = "amino_acid_reference_sequence_fid"), inverseJoinColumns = @JoinColumn(name = "amino_acid_reference_coordinate_fid"))
    private Set<AminoAcidReferenceCoordinate> aminoAcidReferenceCoordinates;

    public AminoAcidReferenceSequence() {
        super();
    }

    public Set<AminoAcidReferenceCoordinate> getAminoAcidReferenceCoordinates() {
        return aminoAcidReferenceCoordinates;
    }

    public void setAminoAcidReferenceCoordinates(Set<AminoAcidReferenceCoordinate> aminoAcidReferenceCoordinates) {
        this.aminoAcidReferenceCoordinates = aminoAcidReferenceCoordinates;
    }

}
