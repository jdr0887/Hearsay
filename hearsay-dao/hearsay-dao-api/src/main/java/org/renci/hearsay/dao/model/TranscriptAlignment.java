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

import org.renci.hearsay.dao.model.Region;

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
    @JoinColumn(name = "translation_sequence_fid")
    private TranslationRefSeq translationSequence;

    @ManyToOne
    @JoinColumn(name = "transcript_sequence_fid")
    private TranscriptRefSeq transcriptSequence;

    @Column(name = "genomic_start")
    private Integer genomicStart;

    @Column(name = "genomic_stop")
    private Integer genomicStop;

    @Column(name = "strand_type")
    @Enumerated(EnumType.STRING)
    private StrandType strandType;

    @OneToMany(mappedBy = "mappedTranscript", fetch = FetchType.EAGER)
    @OrderBy("regionStart ASC")
    private Set<Region> regions;

    public TranscriptAlignment() {
        super();
    }

}
