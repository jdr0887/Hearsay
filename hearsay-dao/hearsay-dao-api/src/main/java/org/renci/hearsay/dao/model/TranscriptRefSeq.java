package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "transcriptSequence")
@XmlType(name = "TranscriptSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript_sequence")
public class TranscriptRefSeq extends ReferenceSequence {

    private static final long serialVersionUID = 7343059748383867273L;

    @ManyToOne
    @JoinColumn(name = "gene_fid")
    private Gene gene;

    @OneToMany(mappedBy = "transcriptSequence", fetch = FetchType.EAGER)
    private Set<TranscriptAlignment> alignments;

    public TranscriptRefSeq() {
        super();
    }

}
