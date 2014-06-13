package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "translationVariant")
@XmlType(name = "TranslationVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "translation_variant")
public class TranslationVariant extends BaseEntity {

    private static final long serialVersionUID = 1803101225548740460L;

    @Column(name = "position")
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "translation_fid")
    private Translation translation;

    @ManyToOne
    @JoinColumn(name = "variant_effect_fid")
    private VariantEffect variantEffect;

    public TranslationVariant() {
        super();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public VariantEffect getVariantEffect() {
        return variantEffect;
    }

    public void setVariantEffect(VariantEffect variantEffect) {
        this.variantEffect = variantEffect;
    }

    @Override
    public String toString() {
        return String.format("TranslationVariant [position=%s, id=%s]", position, id);
    }

}
