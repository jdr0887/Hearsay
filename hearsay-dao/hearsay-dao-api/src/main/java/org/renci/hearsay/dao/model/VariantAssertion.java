package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "variantAssertion")
@XmlType(name = "VariantAssertion")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_assertion")
@NamedQueries({ @NamedQuery(name = "VariantAssertion.findByGenomicVariantId", query = "SELECT a FROM VariantAssertion a where a.genomicVariant.id = :id") })
public class VariantAssertion implements Persistable {

    private static final long serialVersionUID = 470960536404127678L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variant_assertion_id_seq")
    @SequenceGenerator(name = "variant_assertion_id_seq", sequenceName = "variant_assertion_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "accession")
    private String accession;

    @Column(name = "version")
    private Integer version;

    @Column(name = "assertion")
    private String assertion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genomic_variant_fid")
    private GenomicVariant genomicVariant;

    public VariantAssertion() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
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
        int result = 1;
        result = prime * result + ((accession == null) ? 0 : accession.hashCode());
        result = prime * result + ((assertion == null) ? 0 : assertion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
