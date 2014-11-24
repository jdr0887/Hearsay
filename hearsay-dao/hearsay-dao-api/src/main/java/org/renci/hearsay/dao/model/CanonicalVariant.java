package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "canonicalVariant")
@XmlType(name = "CanonicalVariant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "canonical_variant")
public class CanonicalVariant extends BaseEntity {

    private static final long serialVersionUID = -9258968674837262L;

    @OneToMany(mappedBy = "canonicalVariant", fetch = FetchType.EAGER)
    private Set<VariantRepresentation> variants;

    public CanonicalVariant() {
        super();
    }

    public Set<VariantRepresentation> getVariants() {
        return variants;
    }

    public void setVariants(Set<VariantRepresentation> variants) {
        this.variants = variants;
    }

}
