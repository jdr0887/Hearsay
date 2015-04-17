package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.jdbc.ContainerTable;
import org.apache.openjpa.persistence.jdbc.Index;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "genomicReferenceSequence")
@XmlType(name = "GenomicReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genomic_reference_sequence")
public class GenomicReferenceSequence extends ReferenceSequence {

    private static final long serialVersionUID = 604152635458911306L;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @XmlElementWrapper(name = "genomicReferenceCoordinates")
    @XmlElement(name = "genomicReferenceCoordinate")
    @ManyToMany(targetEntity = AminoAcidReferenceSequence.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "genomic_reference_sequence_genomic_reference_coordinate", joinIndex = @Index(columnNames = { "genomic_reference_sequence_fid" }))
    @JoinTable(name = "genomic_reference_sequence_genomic_reference_coordinate", joinColumns = @JoinColumn(name = "genomic_reference_sequence_fid"), inverseJoinColumns = @JoinColumn(name = "genomic_reference_coordinate_fid"))
    private Set<GenomicReferenceCoordinate> genomicReferenceCoordinates;

    public GenomicReferenceSequence() {
        super();
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public Set<GenomicReferenceCoordinate> getGenomicReferenceCoordinates() {
        return genomicReferenceCoordinates;
    }

    public void setGenomicReferenceCoordinates(Set<GenomicReferenceCoordinate> genomicReferenceCoordinates) {
        this.genomicReferenceCoordinates = genomicReferenceCoordinates;
    }

    @Override
    public String toString() {
        return String.format("GenomicReferenceSequence [id=%s, identifier=%s]", id, identifier);
    }

}
