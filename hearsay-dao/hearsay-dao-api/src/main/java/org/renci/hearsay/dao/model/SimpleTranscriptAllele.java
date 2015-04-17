package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "simpleTranscriptAllele")
@XmlType(name = "SimpleTranscriptAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_transcript_allele")
public class SimpleTranscriptAllele extends SimpleNucleotideAllele {

    private static final long serialVersionUID = -1085511056973233814L;

    @XmlElementWrapper(name = "simpleTranscriptAlleles")
    @XmlElement(name = "simpleTranscriptAllele")
    @ManyToMany(mappedBy = "simpleTranscriptAlleles")
    private Set<TranscriptReferenceCoordinate> transcriptReferenceCoordinates;

    public SimpleTranscriptAllele() {
        super();
    }

    public Set<TranscriptReferenceCoordinate> getTranscriptReferenceCoordinates() {
        return transcriptReferenceCoordinates;
    }

    public void setTranscriptReferenceCoordinates(Set<TranscriptReferenceCoordinate> transcriptReferenceCoordinates) {
        this.transcriptReferenceCoordinates = transcriptReferenceCoordinates;
    }

    @Override
    public String toString() {
        return String.format("SimpleTranscriptAllele [id=%s, allele=%s]", id, allele);
    }

}
