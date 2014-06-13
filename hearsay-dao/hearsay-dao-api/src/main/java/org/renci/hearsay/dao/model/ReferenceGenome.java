package org.renci.hearsay.dao.model;

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
@NamedQueries({ @NamedQuery(name = "ReferenceGenome.findAll", query = "SELECT a FROM ReferenceGenome a") })
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
        return referenceSequences;
    }

    public void setReferenceSequences(Set<ReferenceSequence> referenceSequences) {
        this.referenceSequences = referenceSequences;
    }

    @Override
    public String toString() {
        return String.format("ReferenceGenome [source=%s, version=%s, id=%s]", source, version, id);
    }

}
