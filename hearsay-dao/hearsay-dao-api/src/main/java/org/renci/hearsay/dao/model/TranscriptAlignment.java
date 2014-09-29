package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "transcriptAlignment")
@XmlType(name = "TranscriptAlignment")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript_alignment")
public class TranscriptAlignment extends BaseEntity {

    private static final long serialVersionUID = 8025021469243818100L;

    @ManyToOne
    @JoinColumn(name = "translation_ref_seq_fid")
    private TranslationRefSeq translationRefSeq;

    @ManyToOne
    @JoinColumn(name = "transcript_ref_seq_fid")
    private TranscriptRefSeq transcriptRefSeq;

    @Column(name = "genomic_start")
    private Integer genomicStart;

    @Column(name = "genomic_stop")
    private Integer genomicStop;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    @OneToMany(mappedBy = "transcriptAlignment", fetch = FetchType.EAGER)
    @OrderBy("regionStart ASC")
    private Set<Region> regions;

    public TranscriptAlignment() {
        super();
    }

    public TranslationRefSeq getTranslationRefSeq() {
        return translationRefSeq;
    }

    public void setTranslationRefSeq(TranslationRefSeq translationRefSeq) {
        this.translationRefSeq = translationRefSeq;
    }

    public TranscriptRefSeq getTranscriptRefSeq() {
        return transcriptRefSeq;
    }

    public void setTranscriptRefSeq(TranscriptRefSeq transcriptRefSeq) {
        this.transcriptRefSeq = transcriptRefSeq;
    }

    public Integer getGenomicStart() {
        return genomicStart;
    }

    public void setGenomicStart(Integer genomicStart) {
        this.genomicStart = genomicStart;
    }

    public Integer getGenomicStop() {
        return genomicStop;
    }

    public void setGenomicStop(Integer genomicStop) {
        this.genomicStop = genomicStop;
    }

    public StrandType getStrandType() {
        return strandType;
    }

    public void setStrandType(StrandType strandType) {
        this.strandType = strandType;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    @Override
    public String toString() {
        return String.format("TranscriptAlignment [id=%s, genomicStart=%s, genomicStop=%s, strandType=%s, regions=%s]",
                id, genomicStart, genomicStop, strandType, regions);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((genomicStart == null) ? 0 : genomicStart.hashCode());
        result = prime * result + ((genomicStop == null) ? 0 : genomicStop.hashCode());
        result = prime * result + ((regions == null) ? 0 : regions.hashCode());
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
        TranscriptAlignment other = (TranscriptAlignment) obj;
        if (genomicStart == null) {
            if (other.genomicStart != null)
                return false;
        } else if (!genomicStart.equals(other.genomicStart))
            return false;
        if (genomicStop == null) {
            if (other.genomicStop != null)
                return false;
        } else if (!genomicStop.equals(other.genomicStop))
            return false;
        if (regions == null) {
            if (other.regions != null)
                return false;
        } else if (!regions.equals(other.regions))
            return false;
        if (strandType != other.strandType)
            return false;
        return true;
    }

}
