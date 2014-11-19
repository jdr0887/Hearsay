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
@XmlRootElement(name = "genomicVariant")
@XmlType(name = "GenomicVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genomic_variant")
public class GenomicVariant extends VariantRepresentation {

    private static final long serialVersionUID = 8070379578229091997L;

    @Column(name = "ref_allele")
    private String referenceAllele;

    @Column(name = "var_allele")
    private String variantAllele;

    public GenomicVariant() {
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

}
