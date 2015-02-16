package org.renci.hearsay.dao.model.allele;

import java.util.Set;

import javax.persistence.Column;
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
@XmlRootElement(name = "referenceGenome")
@XmlType(name = "ReferenceGenome")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "reference_genome")
public class ReferenceGenome extends BaseEntity {

    private static final long serialVersionUID = -225305295285554428L;

    @Column(name = "name")
    private String name;

    @XmlElementWrapper(name = "chromosomeReferenceSequences")
    @XmlElement(name = "chromosomeReferenceSequence")
    @ManyToMany(mappedBy = "referenceGenomes")
    private Set<ChromosomeReferenceSequence> chromosomeReferenceSequences;

    public ReferenceGenome() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ChromosomeReferenceSequence> getChromosomeReferenceSequences() {
        return chromosomeReferenceSequences;
    }

    public void setChromosomeReferenceSequences(Set<ChromosomeReferenceSequence> chromosomeReferenceSequences) {
        this.chromosomeReferenceSequences = chromosomeReferenceSequences;
    }

    @Override
    public String toString() {
        return String.format("ReferenceGenome [id=%s, name=%s]", id, name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        ReferenceGenome other = (ReferenceGenome) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
