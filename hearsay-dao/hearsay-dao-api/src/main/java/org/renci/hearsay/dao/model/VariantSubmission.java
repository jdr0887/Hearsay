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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "variantSubmission")
@XmlType(name = "VariantSubmission")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_submission")
public class VariantSubmission extends BaseEntity {

    private static final long serialVersionUID = -3534374298371599509L;

    @Column(name = "hgvs")
    private String hgvs;

    @Column(name = "refseq")
    private String refseq;

    @Column(name = "start")
    private Integer start;

    @Column(name = "stop")
    private Integer stop;

    @Column(name = "ref_allele")
    private String referenceAllele;

    @Column(name = "var_allele")
    private String variantAllele;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "variant_assertion_fid")
    private VariantAssertion variantAssertion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "canonical_variant_fid")
    private CanonicalVariant canonicalVariant;

    public VariantSubmission() {
        super();
    }

    public String getHgvs() {
        return hgvs;
    }

    public void setHgvs(String hgvs) {
        this.hgvs = hgvs;
    }

    public String getRefseq() {
        return refseq;
    }

    public void setRefseq(String refseq) {
        this.refseq = refseq;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
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

    public VariantAssertion getVariantAssertion() {
        return variantAssertion;
    }

    public void setVariantAssertion(VariantAssertion variantAssertion) {
        this.variantAssertion = variantAssertion;
    }

    public CanonicalVariant getCanonicalVariant() {
        return canonicalVariant;
    }

    public void setCanonicalVariant(CanonicalVariant canonicalVariant) {
        this.canonicalVariant = canonicalVariant;
    }

    @Override
    public String toString() {
        return String.format(
                "VariantSubmission [hgvs=%s, refseq=%s, start=%s, stop=%s, referenceAllele=%s, variantAllele=%s]",
                hgvs, refseq, start, stop, referenceAllele, variantAllele);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((hgvs == null) ? 0 : hgvs.hashCode());
        result = prime * result + ((referenceAllele == null) ? 0 : referenceAllele.hashCode());
        result = prime * result + ((refseq == null) ? 0 : refseq.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((stop == null) ? 0 : stop.hashCode());
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
        VariantSubmission other = (VariantSubmission) obj;
        if (hgvs == null) {
            if (other.hgvs != null)
                return false;
        } else if (!hgvs.equals(other.hgvs))
            return false;
        if (referenceAllele == null) {
            if (other.referenceAllele != null)
                return false;
        } else if (!referenceAllele.equals(other.referenceAllele))
            return false;
        if (refseq == null) {
            if (other.refseq != null)
                return false;
        } else if (!refseq.equals(other.refseq))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (stop == null) {
            if (other.stop != null)
                return false;
        } else if (!stop.equals(other.stop))
            return false;
        if (variantAllele == null) {
            if (other.variantAllele != null)
                return false;
        } else if (!variantAllele.equals(other.variantAllele))
            return false;
        return true;
    }

}
