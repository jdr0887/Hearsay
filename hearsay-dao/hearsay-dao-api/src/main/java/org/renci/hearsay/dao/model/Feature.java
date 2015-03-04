package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "feature")
@XmlType(name = "Feature")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "feature")
@NamedQueries({ @NamedQuery(name = "Feature.findByTranscriptRefSeqId", query = "SELECT a FROM Feature a where a.transcriptRefSeq.id = :id order by a.regionStart") })
public class Feature implements Persistable {

    private static final long serialVersionUID = -3134761754434008259L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_id_seq")
    @SequenceGenerator(name = "feature_id_seq", sequenceName = "feature_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "region_start")
    private Integer regionStart;

    @Column(name = "region_stop")
    private Integer regionStop;

    @Lob
    @Column(name = "note")
    private String note;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transcript_refseq_fid")
    private TranscriptRefSeq transcriptRefSeq;

    public Feature() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegionStart() {
        return regionStart;
    }

    public void setRegionStart(Integer regionStart) {
        this.regionStart = regionStart;
    }

    public Integer getRegionStop() {
        return regionStop;
    }

    public void setRegionStop(Integer regionStop) {
        this.regionStop = regionStop;
    }

    public TranscriptRefSeq getTranscriptRefSeq() {
        return transcriptRefSeq;
    }

    public void setTranscriptRefSeq(TranscriptRefSeq transcriptRefSeq) {
        this.transcriptRefSeq = transcriptRefSeq;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("Feature [id=%s, regionStart=%s, regionStop=%s, note=%s]", id, regionStart, regionStop,
                note);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((regionStart == null) ? 0 : regionStart.hashCode());
        result = prime * result + ((regionStop == null) ? 0 : regionStop.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feature other = (Feature) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (regionStart == null) {
            if (other.regionStart != null)
                return false;
        } else if (!regionStart.equals(other.regionStart))
            return false;
        if (regionStop == null) {
            if (other.regionStop != null)
                return false;
        } else if (!regionStop.equals(other.regionStop))
            return false;
        return true;
    }

}
