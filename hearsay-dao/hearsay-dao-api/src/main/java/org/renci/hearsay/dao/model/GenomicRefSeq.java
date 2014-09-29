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

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    @Override
    public String toString() {
        return String.format("GenomicRefSeq [accession=%s, id=%s, chromosome=%s]", accession, id, chromosome);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((chromosome == null) ? 0 : chromosome.hashCode());
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
        GenomicRefSeq other = (GenomicRefSeq) obj;
        if (chromosome == null) {
            if (other.chromosome != null)
                return false;
        } else if (!chromosome.equals(other.chromosome))
            return false;
        return true;
    }

}
