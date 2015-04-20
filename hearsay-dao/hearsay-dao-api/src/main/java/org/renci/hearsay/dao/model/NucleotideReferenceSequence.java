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
@XmlRootElement(name = "nucleotideReferenceSequence")
@XmlType(name = "NucleotideReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "nucleotide_reference_sequence")
public class NucleotideReferenceSequence extends ReferenceSequence {

    private static final long serialVersionUID = 604152635458911306L;

    public NucleotideReferenceSequence() {
        super();
    }

    @Override
    public String toString() {
        return String.format("NucleotideReferenceSequence [id=%s]", id);
    }

}
