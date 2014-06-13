package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "genomicSequenceVariant")
@XmlType(name = "GenomicSequenceVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genomic_sequence_variant")
public class GenomicSequenceVariant extends BaseEntity {

    private static final long serialVersionUID = -5253028022618964763L;

    @Column(name = "position")
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "reference_sequence_fid")
    private ReferenceSequence referenceSequence;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "genomic_sequence_variant_variant_effect", joinColumns = @JoinColumn(name = "genomic_sequence_variant_id"), inverseJoinColumns = @JoinColumn(name = "variant_effect_id"))
    private Set<VariantEffect> variantEffects;

    public GenomicSequenceVariant() {
        super();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public ReferenceSequence getReferenceSequence() {
        return referenceSequence;
    }

    public void setReferenceSequence(ReferenceSequence referenceSequence) {
        this.referenceSequence = referenceSequence;
    }

    public Set<VariantEffect> getVariantEffects() {
        return variantEffects;
    }

    public void setVariantEffects(Set<VariantEffect> variantEffects) {
        this.variantEffects = variantEffects;
    }

    @Override
    public String toString() {
        return String.format("GenomicSequenceVariant [position=%s, id=%s]", position, id);
    }

}
