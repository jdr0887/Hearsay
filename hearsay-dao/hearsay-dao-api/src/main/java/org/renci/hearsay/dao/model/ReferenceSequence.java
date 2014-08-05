package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "referenceSequence")
@XmlType(name = "ReferenceSequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "reference_sequence", uniqueConstraints = { @UniqueConstraint(columnNames = { "accession" }) })
@NamedQueries({
        @NamedQuery(name = "ReferenceSequence.findAll", query = "SELECT a FROM ReferenceSequence a"),
        @NamedQuery(name = "ReferenceSequence.findByAccession", query = "SELECT a FROM ReferenceSequence a where a.accession = :accession") })
public class ReferenceSequence extends BaseEntity {

    private static final long serialVersionUID = 1327461104847251913L;

    @Column(name = "accession")
    private String accession;

    @ManyToMany(mappedBy = "referenceSequences")
    private Set<ReferenceGenome> referenceGenomes;

    public ReferenceSequence() {
        super();
    }

    public ReferenceSequence(String accession) {
        super();
        this.accession = accession;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public Set<ReferenceGenome> getReferenceGenomes() {
        if (referenceGenomes == null) {
            referenceGenomes = new HashSet<ReferenceGenome>();
        }
        return referenceGenomes;
    }

    public void setReferenceGenomes(Set<ReferenceGenome> referenceGenomes) {
        this.referenceGenomes = referenceGenomes;
    }

    @Override
    public String toString() {
        return String.format("ReferenceSequence [accession=%s, id=%s]", accession, id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((accession == null) ? 0 : accession.hashCode());
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
        ReferenceSequence other = (ReferenceSequence) obj;
        if (accession == null) {
            if (other.accession != null)
                return false;
        } else if (!accession.equals(other.accession))
            return false;
        return true;
    }

}
