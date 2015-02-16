package org.renci.hearsay.dao.model.allele;

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
@XmlRootElement(name = "simpleGenomicAllele")
@XmlType(name = "SimpleGenomicAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_genomic_allele")
public class SimpleGenomicAllele extends SimpleNucleotideAllele {

    private static final long serialVersionUID = -1085511056973233814L;

    @XmlElementWrapper(name = "simpleGenomicAlleles")
    @XmlElement(name = "simpleGenomicAllele")
    @ManyToMany(mappedBy = "simpleGenomicAlleles")
    private Set<GenomicReferenceCoordinate> genomicReferenceCoordinates;

    public SimpleGenomicAllele() {
        super();
    }

    public Set<GenomicReferenceCoordinate> getGenomicReferenceCoordinates() {
        return genomicReferenceCoordinates;
    }

    public void setGenomicReferenceCoordinates(Set<GenomicReferenceCoordinate> genomicReferenceCoordinates) {
        this.genomicReferenceCoordinates = genomicReferenceCoordinates;
    }

    @Override
    public String toString() {
        return String.format("SimpleGenomicAllele [id=%s, allele=%s]", id, allele);
    }

}
