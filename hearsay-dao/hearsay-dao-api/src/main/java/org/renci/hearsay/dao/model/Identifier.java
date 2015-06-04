package org.renci.hearsay.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlType(name = "Identifier")
@XmlRootElement(name = "identifier")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "identifier")
public class Identifier implements Persistable {

    private static final long serialVersionUID = 6208779597138958791L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier_id_seq")
    @SequenceGenerator(name = "identifier_id_seq", sequenceName = "identifier_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "system")
    private String system;

    @Column(name = "value")
    private String value;

    @XmlTransient
    @ManyToMany(targetEntity = Gene.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "gene_identifier", joinColumns = @JoinColumn(name = "identifier_fid"), inverseJoinColumns = @JoinColumn(name = "gene_fid"))
    private List<Gene> genes;

    @XmlTransient
    @ManyToMany(targetEntity = GenomeReference.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "genome_identifier", joinColumns = @JoinColumn(name = "identifier_fid"), inverseJoinColumns = @JoinColumn(name = "genome_reference_fid"))
    private List<GenomeReference> genomicReferences;

    public Identifier() {
        super();
    }

    public Identifier(String system, String value) {
        super();
        this.system = system;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Gene> getGenes() {
        return genes;
    }

    public void setGenes(List<Gene> genes) {
        this.genes = genes;
    }

    public List<GenomeReference> getGenomicReferences() {
        return genomicReferences;
    }

    public void setGenomicReferences(List<GenomeReference> genomicReferences) {
        this.genomicReferences = genomicReferences;
    }

    @Override
    public String toString() {
        return String.format("Identifier [id=%s, system=%s, value=%s]", id, system, value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((system == null) ? 0 : system.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Identifier other = (Identifier) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (system == null) {
            if (other.system != null)
                return false;
        } else if (!system.equals(other.system))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}
