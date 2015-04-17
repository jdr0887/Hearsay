package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.jdbc.Index;
import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "referenceGenome")
@XmlType(name = "ReferenceGenome")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "reference_genome")
public class ReferenceGenome implements Persistable {

    private static final long serialVersionUID = -225305295285554428L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reference_genome_id_seq")
    @SequenceGenerator(name = "reference_genome_id_seq", sequenceName = "reference_genome_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Index
    @Column(name = "name")
    private String name;

    @XmlElementWrapper(name = "chromosomeReferenceSequences")
    @XmlElement(name = "chromosomeReferenceSequence")
    @ManyToMany(mappedBy = "referenceGenomes")
    private Set<ChromosomeReferenceSequence> chromosomeReferenceSequences;

    public ReferenceGenome() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
