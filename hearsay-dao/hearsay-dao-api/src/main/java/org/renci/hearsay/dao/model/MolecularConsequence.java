package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "molecularConsequence")
@XmlType(name = "MolecularConsequence")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "molecular_consequence")
public class MolecularConsequence implements Persistable {

    private static final long serialVersionUID = -1712420135432481226L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "molecular_consequence_id_seq")
    @SequenceGenerator(name = "molecular_consequence_id_seq", sequenceName = "molecular_consequence_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "sequence_ontology_term")
    private String sequenceOntologyTerm;

    @ManyToOne
    @JoinColumn(name = "variant_fid")
    private VariantRepresentation variant;

    public MolecularConsequence() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequenceOntologyTerm() {
        return sequenceOntologyTerm;
    }

    public void setSequenceOntologyTerm(String sequenceOntologyTerm) {
        this.sequenceOntologyTerm = sequenceOntologyTerm;
    }

    public VariantRepresentation getVariant() {
        return variant;
    }

    public void setVariant(VariantRepresentation variant) {
        this.variant = variant;
    }

    @Override
    public String toString() {
        return String.format("MolecularConsequence [id=%s, sequenceOntologyTerm=%s]", id, sequenceOntologyTerm);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((sequenceOntologyTerm == null) ? 0 : sequenceOntologyTerm.hashCode());
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
        MolecularConsequence other = (MolecularConsequence) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (sequenceOntologyTerm == null) {
            if (other.sequenceOntologyTerm != null)
                return false;
        } else if (!sequenceOntologyTerm.equals(other.sequenceOntologyTerm))
            return false;
        return true;
    }

}
