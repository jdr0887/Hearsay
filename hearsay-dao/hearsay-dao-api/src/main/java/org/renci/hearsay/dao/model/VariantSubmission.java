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

    @ManyToOne
    @JoinColumn(name = "variant_assertion_fid")
    private VariantAssertion variantAssertion;

    @ManyToOne
    @JoinColumn(name = "canonical_variant_fid")
    private CanonicalVariant canonicalVariant;

    public VariantSubmission() {
        super();
    }

}
