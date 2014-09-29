package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "genomicSequence")
@XmlType(name = "GenomicSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genomic_sequence")
public class GenomicRefSeq extends ReferenceSequence {

    private static final long serialVersionUID = -1855771566037222326L;

    @Column(name = "chromosome")
    private String chromosome;

    public GenomicRefSeq() {
        super();
    }

}
