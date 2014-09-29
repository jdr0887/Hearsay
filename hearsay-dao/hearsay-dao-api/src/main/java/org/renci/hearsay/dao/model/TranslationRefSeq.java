package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "translationSequence")
@XmlType(name = "TranslationSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "translation_sequence")
public class TranslationRefSeq extends ReferenceSequence {

    private static final long serialVersionUID = 8028669733155889888L;

    @OneToMany(mappedBy = "transcriptRefSeq", fetch = FetchType.EAGER)
    private Set<TranscriptAlignment> alignments;

    public TranslationRefSeq() {
        super();
    }

    public Set<TranscriptAlignment> getAlignments() {
        return alignments;
    }

    public void setAlignments(Set<TranscriptAlignment> alignments) {
        this.alignments = alignments;
    }
    
}
