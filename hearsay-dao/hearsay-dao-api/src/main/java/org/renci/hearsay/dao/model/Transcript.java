package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

    @Column(name = "gene_id")
    private Long geneId;

    @Column(name = "opm_document_id")
    private Long documentId;

    @Column(name = "gene_name")
    private String geneName;

    @Column(name = "hgnc_symbol")
    private String hgncSymbol;

    @Column(name = "map_index")
    private Integer mapIndex;

    @Column(name = "total_maps")
    private Integer mapsTotal;

    @Column(name = "refseq_accession")
    private String refSeqAccession;

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
    private Set<TranscriptInterval> exons;

    public Transcript() {
        super();
    }

    public Long getGeneId() {
        return geneId;
    }

    public void setGeneId(Long geneId) {
        this.geneId = geneId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
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

    public String getRefSeqAccession() {
        return refSeqAccession;
    }

    public void setRefSeqAccession(String refSeqAccession) {
        this.refSeqAccession = refSeqAccession;
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

    public Set<TranscriptInterval> getExons() {
        return exons;
    }

    public void setExons(Set<TranscriptInterval> exons) {
        this.exons = exons;
    }

    @Override
    public String toString() {
        return String
                .format("Transcript [geneId=%s, geneName=%s, hgncSymbol=%s, mapIndex=%s, mapsTotal=%s, refSeqAccession=%s, proteinRefSeqAccession=%s, strandType=%s, boundsStart=%s, boundsEnd=%s, codingStart=%s, codingEnd=%s]",
                        geneId, geneName, hgncSymbol, mapIndex, mapsTotal, refSeqAccession, proteinRefSeqAccession,
                        strandType, boundsStart, boundsEnd, codingStart, codingEnd);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((boundsEnd == null) ? 0 : boundsEnd.hashCode());
        result = prime * result + ((boundsStart == null) ? 0 : boundsStart.hashCode());
        result = prime * result + ((codingEnd == null) ? 0 : codingEnd.hashCode());
        result = prime * result + ((codingStart == null) ? 0 : codingStart.hashCode());
        result = prime * result + ((geneId == null) ? 0 : geneId.hashCode());
        result = prime * result + ((geneName == null) ? 0 : geneName.hashCode());
        result = prime * result + ((hgncSymbol == null) ? 0 : hgncSymbol.hashCode());
        result = prime * result + ((mapIndex == null) ? 0 : mapIndex.hashCode());
        result = prime * result + ((mapsTotal == null) ? 0 : mapsTotal.hashCode());
        result = prime * result + ((proteinRefSeqAccession == null) ? 0 : proteinRefSeqAccession.hashCode());
        result = prime * result + ((refSeqAccession == null) ? 0 : refSeqAccession.hashCode());
        result = prime * result + ((strandType == null) ? 0 : strandType.hashCode());
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
        if (boundsEnd == null) {
            if (other.boundsEnd != null)
                return false;
        } else if (!boundsEnd.equals(other.boundsEnd))
            return false;
        if (boundsStart == null) {
            if (other.boundsStart != null)
                return false;
        } else if (!boundsStart.equals(other.boundsStart))
            return false;
        if (codingEnd == null) {
            if (other.codingEnd != null)
                return false;
        } else if (!codingEnd.equals(other.codingEnd))
            return false;
        if (codingStart == null) {
            if (other.codingStart != null)
                return false;
        } else if (!codingStart.equals(other.codingStart))
            return false;
        if (geneId == null) {
            if (other.geneId != null)
                return false;
        } else if (!geneId.equals(other.geneId))
            return false;
        if (geneName == null) {
            if (other.geneName != null)
                return false;
        } else if (!geneName.equals(other.geneName))
            return false;
        if (hgncSymbol == null) {
            if (other.hgncSymbol != null)
                return false;
        } else if (!hgncSymbol.equals(other.hgncSymbol))
            return false;
        if (mapIndex == null) {
            if (other.mapIndex != null)
                return false;
        } else if (!mapIndex.equals(other.mapIndex))
            return false;
        if (mapsTotal == null) {
            if (other.mapsTotal != null)
                return false;
        } else if (!mapsTotal.equals(other.mapsTotal))
            return false;
        if (proteinRefSeqAccession == null) {
            if (other.proteinRefSeqAccession != null)
                return false;
        } else if (!proteinRefSeqAccession.equals(other.proteinRefSeqAccession))
            return false;
        if (refSeqAccession == null) {
            if (other.refSeqAccession != null)
                return false;
        } else if (!refSeqAccession.equals(other.refSeqAccession))
            return false;
        if (strandType != other.strandType)
            return false;
        return true;
    }

}
