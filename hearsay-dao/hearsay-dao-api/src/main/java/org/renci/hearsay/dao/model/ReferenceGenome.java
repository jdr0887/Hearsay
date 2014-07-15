package org.renci.hearsay.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@NamedQueries({
        @NamedQuery(name = "ReferenceGenome.findAll", query = "SELECT a FROM ReferenceGenome a"),
        @NamedQuery(name = "ReferenceGenome.findBySource", query = "SELECT a FROM ReferenceGenome a where a.source = :source"),
        @NamedQuery(name = "ReferenceGenome.findBySourceAndVersion", query = "SELECT a FROM ReferenceGenome a where a.source = :source and a.version = :version") })
public class ReferenceGenome extends BaseEntity {

    private static final long serialVersionUID = -5286845209354037261L;

    @Column(name = "source")
    private String source;

    @Column(name = "version")
    private String version;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "reference_genome_reference_sequence", joinColumns = @JoinColumn(name = "reference_genome_fid"), inverseJoinColumns = @JoinColumn(name = "reference_sequence_id"))
    private Set<ReferenceSequence> referenceSequences;

    public ReferenceGenome() {
        super();
    }

    public ReferenceGenome(String source, String version) {
        super();
        this.source = source;
        this.version = version;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<ReferenceSequence> getReferenceSequences() {
        if (referenceSequences == null) {
            referenceSequences = new HashSet<ReferenceSequence>();
        }
        return referenceSequences;
    }

    public void setReferenceSequences(Set<ReferenceSequence> referenceSequences) {
        this.referenceSequences = referenceSequences;
    }

    @Override
    public String toString() {
        return String.format("ReferenceGenome [source=%s, version=%s, id=%s]", source, version, id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
