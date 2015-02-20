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
@XmlRootElement(name = "variantAssertion")
@XmlType(name = "VariantAssertion")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_assertion")
public class VariantAssertion extends BaseEntity {

    private static final long serialVersionUID = 470960536404127678L;

    @Column(name = "accession")
    private String accession;

    @Column(name = "version")
    private String version;

    @Column(name = "assertion")
    private String assertion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genomic_variant_fid")
    private GenomicVariant genomicVariant;

    public VariantAssertion() {
        super();
    }

    public GenomicVariant getGenomicVariant() {
        return genomicVariant;
    }

    public void setGenomicVariant(GenomicVariant genomicVariant) {
        this.genomicVariant = genomicVariant;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAssertion() {
        return assertion;
    }

    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    @Override
    public String toString() {
        return String.format("VariantAssertion [id=%s, accession=%s, version=%s, assertion=%s]", id, accession,
                version, assertion);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((accession == null) ? 0 : accession.hashCode());
        result = prime * result + ((assertion == null) ? 0 : assertion.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        VariantAssertion other = (VariantAssertion) obj;
        if (accession == null) {
            if (other.accession != null)
                return false;
        } else if (!accession.equals(other.accession))
            return false;
        if (assertion == null) {
            if (other.assertion != null)
                return false;
        } else if (!assertion.equals(other.assertion))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
