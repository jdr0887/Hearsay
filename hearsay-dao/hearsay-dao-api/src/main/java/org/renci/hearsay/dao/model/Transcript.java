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

    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @Column(name = "accession")
    private String accession;

    @Column(name = "bounds_start")
    private Integer boundsStart;

    @Column(name = "bounds_end")
    private Integer boundsEnd;

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

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public Integer getBoundsStart() {
        return boundsStart;
    }

    public void setBoundsStart(Integer boundsStart) {
        this.boundsStart = boundsStart;
    }

    public Integer getBoundsEnd() {
        return boundsEnd;
    }

    public void setBoundsEnd(Integer boundsEnd) {
        this.boundsEnd = boundsEnd;
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
        return String.format("Transcript [accession=%s, boundsStart=%s, boundsEnd=%s, id=%s]", accession, boundsStart,
                boundsEnd, id);
    }

}
