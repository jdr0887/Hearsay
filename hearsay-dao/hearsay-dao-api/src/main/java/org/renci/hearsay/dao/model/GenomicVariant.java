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
@XmlRootElement(name = "genomicVariant")
@XmlType(name = "GenomicVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "genomic_variant")
public class GenomicVariant extends VariantRepresentation {

    private static final long serialVersionUID = 8070379578229091997L;

    @Lob
    @Column(name = "ref_allele")
    private String referenceAllele;

    @Lob
    @Column(name = "var_allele")
    private String variantAllele;

    @Column(name = "chromosome")
    private String chromosome;

    public GenomicVariant() {
        super();
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
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
        return String.format("GenomicVariant [referenceAllele=%s, variantAllele=%s, chromosome=%s]", referenceAllele,
                variantAllele, chromosome);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((chromosome == null) ? 0 : chromosome.hashCode());
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
        GenomicVariant other = (GenomicVariant) obj;
        if (chromosome == null) {
            if (other.chromosome != null)
                return false;
        } else if (!chromosome.equals(other.chromosome))
            return false;
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
