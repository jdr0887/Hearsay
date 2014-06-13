package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
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
@XmlRootElement(name = "variant")
@XmlType(name = "Variant")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant")
public class Variant extends BaseEntity {

    private static final long serialVersionUID = 6938206007642149853L;

    @OneToMany(mappedBy = "variant", fetch = FetchType.LAZY)
    private Set<VariantEffect> variantEffects;

    @Column(name = "opm_doc_id")
    protected Long documentId;

    public Variant() {
        super();
    }

    public Set<VariantEffect> getVariantEffects() {
        return variantEffects;
    }

    public void setVariantEffects(Set<VariantEffect> variantEffects) {
        this.variantEffects = variantEffects;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return String.format("Variant [id=%s, documentId=%s]", id, documentId);
    }

}
