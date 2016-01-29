package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@XmlRootElement(name = "canonicalAlleleReplacement")
@XmlType(propOrder = { "type", "split", "canonicalAllele" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(schema = "hearsay", name = "canonical_allele_replacement")
public class CanonicalAlleleReplacement implements Persistable {

    private static final long serialVersionUID = -5394202230597958273L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contextual_allele_replacement_id_seq")
    @SequenceGenerator(schema = "hearsay", name = "contextual_allele_replacement_id_seq", sequenceName = "contextual_allele_replacement_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CanonicalAlleleReplacementType type;

    @Column(name = "split")
    private Boolean split;

    @ManyToOne
    @JoinColumn(name = "canonical_allele_fid")
    private CanonicalAllele canonicalAllele;

    public CanonicalAlleleReplacement() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CanonicalAlleleReplacementType getType() {
        return type;
    }

    public void setType(CanonicalAlleleReplacementType type) {
        this.type = type;
    }

    public Boolean getSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
    }

    public CanonicalAllele getCanonicalAllele() {
        return canonicalAllele;
    }

    public void setCanonicalAllele(CanonicalAllele canonicalAllele) {
        this.canonicalAllele = canonicalAllele;
    }

    @Override
    public String toString() {
        return String.format("CanonicalAlleleReplacement [id=%s, type=%s, split=%s]", id, type, split);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((split == null) ? 0 : split.hashCode());
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
        CanonicalAlleleReplacement other = (CanonicalAlleleReplacement) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (type != other.type)
            return false;
        if (split == null) {
            if (other.split != null)
                return false;
        } else if (!split.equals(other.split))
            return false;
        return true;
    }

}
