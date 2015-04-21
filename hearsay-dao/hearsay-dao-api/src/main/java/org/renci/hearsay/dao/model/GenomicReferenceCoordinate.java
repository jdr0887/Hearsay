package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@XmlRootElement(name = "genomicReferenceCoordinate")
@XmlType(name = "GenomicReferenceCoordinate")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genomic_reference_coordinate")
public class GenomicReferenceCoordinate extends NucleotideReferenceCoordinate {

    private static final long serialVersionUID = 5066165250575195465L;

    @XmlElementWrapper(name = "genomicReferenceCoordinates")
    @XmlElement(name = "genomicReferenceCoordinate")
    @ManyToMany(mappedBy = "genomicReferenceCoordinates")
    private Set<GenomicReferenceSequence> genomicReferenceSequences;

    @OneToOne
    @JoinColumn(name = "intron_offset_fid")
    private IntronOffset intronOffset;

    @XmlElementWrapper(name = "simpleGenomicAlleles")
    @XmlElement(name = "simpleGenomicAllele")
    @ManyToMany(targetEntity = SimpleGenomicAllele.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "genomic_reference_coordinate_simple_genomic_allele", joinIndex = @Index(columnNames = { "genomic_reference_coordinate_fid" }))
    @JoinTable(name = "genomic_reference_coordinate_simple_genomic_allele", joinColumns = @JoinColumn(name = "genomic_reference_coordinate_fid"), inverseJoinColumns = @JoinColumn(name = "simple_genomic_allele_fid"))
    private Set<SimpleGenomicAllele> simpleGenomicAlleles;

    public GenomicReferenceCoordinate() {
        super();
    }

    public Set<GenomicReferenceSequence> getGenomicReferenceSequences() {
        return genomicReferenceSequences;
    }

    public void setGenomicReferenceSequences(Set<GenomicReferenceSequence> genomicReferenceSequences) {
        this.genomicReferenceSequences = genomicReferenceSequences;
    }

    public IntronOffset getIntronOffset() {
        return intronOffset;
    }

    public void setIntronOffset(IntronOffset intronOffset) {
        this.intronOffset = intronOffset;
    }

    public Set<SimpleGenomicAllele> getSimpleGenomicAlleles() {
        return simpleGenomicAlleles;
    }

    public void setSimpleGenomicAlleles(Set<SimpleGenomicAllele> simpleGenomicAlleles) {
        this.simpleGenomicAlleles = simpleGenomicAlleles;
    }

    @Override
    public String toString() {
        return String.format("GenomicReferenceCoordinate [id=%s, refAllele=%s, start=%s, end=%s]", id, refAllele,
                start, end);
    }

}
