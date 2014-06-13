package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "variantEffect")
@XmlType(name = "VariantEffect")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_effect")
public class VariantEffect extends BaseEntity {

    private static final long serialVersionUID = 6938206007642149853L;

    @Column(name = "sequence_ontology_term")
    private String sequenceOntologyTerm;

    @ManyToOne
    @JoinColumn(name = "variant_fid")
    private Variant variant;

    @OneToMany(mappedBy = "variantEffect", fetch = FetchType.LAZY)
    private Set<TranslationVariant> translationVariants;

    public VariantEffect() {
        super();
    }

    public String getSequenceOntologyTerm() {
        return sequenceOntologyTerm;
    }

    public void setSequenceOntologyTerm(String sequenceOntologyTerm) {
        this.sequenceOntologyTerm = sequenceOntologyTerm;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public Set<TranslationVariant> getTranslationVariants() {
        return translationVariants;
    }

    public void setTranslationVariants(Set<TranslationVariant> translationVariants) {
        this.translationVariants = translationVariants;
    }

    @Override
    public String toString() {
        return String.format("VariantEffect [sequenceOntologyTerm=%s, id=%s]", sequenceOntologyTerm, id);
    }

}
