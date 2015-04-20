package org.renci.hearsay.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "genomicReferenceSequence")
@XmlType(name = "GenomicReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genomic_reference_sequence")
public class GenomicReferenceSequence extends NucleotideReferenceSequence {

    private static final long serialVersionUID = 604152635458911306L;

    public GenomicReferenceSequence() {
        super();
    }

    @Override
    public String toString() {
        return String.format("GenomicReferenceSequence [id=%s]", id);
    }

}
