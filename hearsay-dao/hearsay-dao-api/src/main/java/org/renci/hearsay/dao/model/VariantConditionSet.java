package org.renci.hearsay.dao.model;

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
@XmlRootElement(name = "variantConditionSet")
@XmlType(name = "VariantConditionSet")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_condition_set")
public class VariantConditionSet extends BaseEntity {

    private static final long serialVersionUID = -3887873202398160140L;

    @ManyToOne
    @JoinColumn(name = "resolved_condition_fid")
    private ResolvedCondition resolvedCondition;

    @ManyToOne
    @JoinColumn(name = "canonical_variant_fid")
    private CanonicalVariant canonicalVariant;

    public VariantConditionSet() {
        super();
    }

}
