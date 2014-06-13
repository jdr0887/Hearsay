package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "translation")
@XmlType(name = "Translation")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "translation")
public class Translation extends BaseEntity {

    private static final long serialVersionUID = 6938206007642149853L;

    @Column(name = "accession")
    private String accession;

    @ManyToOne
    @JoinColumn(name = "transcript_fid")
    private Transcript transcript;

    public Translation() {
        super();
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    @Override
    public String toString() {
        return String.format("Translation [accession=%s, id=%s]", accession, id);
    }

}
