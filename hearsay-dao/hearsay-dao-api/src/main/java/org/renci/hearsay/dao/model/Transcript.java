package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "hgnc_symbol")
    private String hgncSymbol;

    @Column(name = "map_index")
    private Integer mapIndex;

    @Column(name = "total_maps")
    private Integer mapsTotal;

    @Column(name = "protein_refseq_accession")
    private String proteinRefSeqAccession;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    @Column(name = "bounds_start")
    private Integer boundsStart;

    @Column(name = "bounds_end")
    private Integer boundsEnd;

    @Column(name = "coding_start")
    private Integer codingStart;

    @Column(name = "coding_end")
    private Integer codingEnd;

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

    public String getHgncSymbol() {
        return hgncSymbol;
    }

    public void setHgncSymbol(String hgncSymbol) {
        this.hgncSymbol = hgncSymbol;
    }

    public Integer getMapIndex() {
        return mapIndex;
    }

    public void setMapIndex(Integer mapIndex) {
        this.mapIndex = mapIndex;
    }

    public Integer getMapsTotal() {
        return mapsTotal;
    }

    public void setMapsTotal(Integer mapsTotal) {
        this.mapsTotal = mapsTotal;
    }

    public String getProteinRefSeqAccession() {
        return proteinRefSeqAccession;
    }

    public void setProteinRefSeqAccession(String proteinRefSeqAccession) {
        this.proteinRefSeqAccession = proteinRefSeqAccession;
    }

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
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

    public Integer getCodingStart() {
        return codingStart;
    }

    public void setCodingStart(Integer codingStart) {
        this.codingStart = codingStart;
    }

    public Integer getCodingEnd() {
        return codingEnd;
    }

    public void setCodingEnd(Integer codingEnd) {
        this.codingEnd = codingEnd;
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
        return String
                .format("Transcript [hgncSymbol=%s, mapIndex=%s, mapsTotal=%s, proteinRefSeqAccession=%s, strandType=%s, boundsStart=%s, boundsEnd=%s, codingStart=%s, codingEnd=%s, id=%s]",
                        hgncSymbol, mapIndex, mapsTotal, proteinRefSeqAccession, strandType, boundsStart, boundsEnd,
                        codingStart, codingEnd, id);
    }

}
