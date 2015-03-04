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
@XmlRootElement(name = "variantConditionSet")
@XmlType(name = "VariantConditionSet")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_condition_set")
public class VariantConditionSet implements Persistable {

    private static final long serialVersionUID = -3887873202398160140L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variant_condition_set_id_seq")
    @SequenceGenerator(name = "variant_condition_set_id_seq", sequenceName = "variant_condition_set_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resolved_condition_fid")
    private ResolvedCondition resolvedCondition;

    @ManyToOne
    @JoinColumn(name = "canonical_variant_fid")
    private CanonicalVariant canonicalVariant;

    public VariantConditionSet() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResolvedCondition getResolvedCondition() {
        return resolvedCondition;
    }

    public void setResolvedCondition(ResolvedCondition resolvedCondition) {
        this.resolvedCondition = resolvedCondition;
    }

    public CanonicalVariant getCanonicalVariant() {
        return canonicalVariant;
    }

    public void setCanonicalVariant(CanonicalVariant canonicalVariant) {
        this.canonicalVariant = canonicalVariant;
    }

    @Override
    public String toString() {
        return String.format("VariantConditionSet [id=%s]", id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        VariantConditionSet other = (VariantConditionSet) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
