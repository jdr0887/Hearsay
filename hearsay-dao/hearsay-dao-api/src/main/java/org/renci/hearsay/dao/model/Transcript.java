package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "transcript")
@XmlType(name = "Transcript")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript")
@NamedQueries({ @NamedQuery(name = "Transcript.findAll", query = "SELECT a FROM Transcript a") })
public class Transcript extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @Column(name = "accession")
    private String genomicAccession;

    @Column(name = "genomic_start")
    private Integer genomicStart;

    @Column(name = "genomic_end")
    private Integer genomicEnd;

    @OneToMany(mappedBy = "transcript", fetch = FetchType.LAZY)
    private Set<MappedTranscript> exons;

    @OneToMany(mappedBy = "transcript", fetch = FetchType.LAZY)
    private Set<Translation> translations;

    public Transcript() {
        super();
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getGenomicAccession() {
        return genomicAccession;
    }

    public void setGenomicAccession(String genomicAccession) {
        this.genomicAccession = genomicAccession;
    }

    public Integer getGenomicStart() {
        return genomicStart;
    }

    public void setGenomicStart(Integer genomicStart) {
        this.genomicStart = genomicStart;
    }

    public Integer getGenomicEnd() {
        return genomicEnd;
    }

    public void setGenomicEnd(Integer genomicEnd) {
        this.genomicEnd = genomicEnd;
    }

    public Set<MappedTranscript> getExons() {
        return exons;
    }

    public void setExons(Set<MappedTranscript> exons) {
        this.exons = exons;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    @Override
    public String toString() {
        return String.format("Transcript [genomicAccession=%s, genomicStart=%s, genomicEnd=%s, id=%s]",
                genomicAccession, genomicStart, genomicEnd, id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((gene == null) ? 0 : gene.hashCode());
        result = prime * result + ((genomicAccession == null) ? 0 : genomicAccession.hashCode());
        result = prime * result + ((genomicEnd == null) ? 0 : genomicEnd.hashCode());
        result = prime * result + ((genomicStart == null) ? 0 : genomicStart.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transcript other = (Transcript) obj;
        if (gene == null) {
            if (other.gene != null)
                return false;
        } else if (!gene.equals(other.gene))
            return false;
        if (genomicAccession == null) {
            if (other.genomicAccession != null)
                return false;
        } else if (!genomicAccession.equals(other.genomicAccession))
            return false;
        if (genomicEnd == null) {
            if (other.genomicEnd != null)
                return false;
        } else if (!genomicEnd.equals(other.genomicEnd))
            return false;
        if (genomicStart == null) {
            if (other.genomicStart != null)
                return false;
        } else if (!genomicStart.equals(other.genomicStart))
            return false;
        return true;
    }

}
