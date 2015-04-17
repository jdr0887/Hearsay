package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@XmlRootElement(name = "chromosomeReferenceSequence")
@XmlType(name = "ChromosomeReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "chromosome_reference_sequence")
public class ChromosomeReferenceSequence extends GenomicReferenceSequence {

    private static final long serialVersionUID = 5109835780098938302L;

    @Column(name = "chr")
    private String chr;

    @XmlElementWrapper(name = "referenceGenomes")
    @XmlElement(name = "referenceGenome")
    @ManyToMany(targetEntity = ChromosomeReferenceSequence.class, cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @ContainerTable(name = "chromosome_reference_sequence_reference_genome", joinIndex = @Index(columnNames = { "chromosome_reference_sequence_fid" }))
    @JoinTable(name = "chromosome_reference_sequence_reference_genome", joinColumns = @JoinColumn(name = "chromosome_reference_sequence_fid"), inverseJoinColumns = @JoinColumn(name = "reference_genome_fid"))
    private Set<ReferenceGenome> referenceGenomes;

    public ChromosomeReferenceSequence() {
        super();
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public Set<ReferenceGenome> getReferenceGenomes() {
        return referenceGenomes;
    }

    public void setReferenceGenomes(Set<ReferenceGenome> referenceGenomes) {
        this.referenceGenomes = referenceGenomes;
    }

}
