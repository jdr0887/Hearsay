package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @Column(name = "ref_allele")
    private String referenceAllele;

    @Column(name = "var_allele")
    private String variantAllele;

    public TranscriptVariant() {
        super();
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
        return String.format("TranscriptVariant [id=%s, referenceAllele=%s, variantAllele=%s]", id, referenceAllele,
                variantAllele);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((referenceAllele == null) ? 0 : referenceAllele.hashCode());
        result = prime * result + ((variantAllele == null) ? 0 : variantAllele.hashCode());
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
        if (variantAllele == null) {
            if (other.variantAllele != null)
                return false;
        } else if (!variantAllele.equals(other.variantAllele))
            return false;
        return true;
    }

}
