package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "transcriptVariant")
@XmlType(name = "TranscriptVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "transcript_variant")
public class TranscriptVariant extends VariantRepresentation {

    private static final long serialVersionUID = 6115001489678011601L;

    @Lob
    @Column(name = "ref_allele")
    private String referenceAllele;

    @Lob
    @Column(name = "var_allele")
    private String variantAllele;

    @Column(name = "variant_effect")
    private String variantEffect;

    @Column(name = "transcript")
    private String transcript;

    public TranscriptVariant() {
        super();
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getVariantEffect() {
        return variantEffect;
    }

    public void setVariantEffect(String variantEffect) {
        this.variantEffect = variantEffect;
    }

    public String getReferenceAllele() {
        return referenceAllele;
    }

    public void setReferenceAllele(String referenceAllele) {
        this.referenceAllele = referenceAllele;
    }

    public String getVariantAllele() {
        return variantAllele;
    }

    public void setVariantAllele(String variantAllele) {
        this.variantAllele = variantAllele;
    }

    @Override
    public String toString() {
        return String.format(
                "TranscriptVariant [referenceAllele=%s, variantAllele=%s, variantEffect=%s, transcript=%s]",
                referenceAllele, variantAllele, variantEffect, transcript);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((referenceAllele == null) ? 0 : referenceAllele.hashCode());
        result = prime * result + ((transcript == null) ? 0 : transcript.hashCode());
        result = prime * result + ((variantAllele == null) ? 0 : variantAllele.hashCode());
        result = prime * result + ((variantEffect == null) ? 0 : variantEffect.hashCode());
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
        TranscriptVariant other = (TranscriptVariant) obj;
        if (referenceAllele == null) {
            if (other.referenceAllele != null)
                return false;
        } else if (!referenceAllele.equals(other.referenceAllele))
            return false;
        if (transcript == null) {
            if (other.transcript != null)
                return false;
        } else if (!transcript.equals(other.transcript))
            return false;
        if (variantAllele == null) {
            if (other.variantAllele != null)
                return false;
        } else if (!variantAllele.equals(other.variantAllele))
            return false;
        if (variantEffect == null) {
            if (other.variantEffect != null)
                return false;
        } else if (!variantEffect.equals(other.variantEffect))
            return false;
        return true;
    }

}
