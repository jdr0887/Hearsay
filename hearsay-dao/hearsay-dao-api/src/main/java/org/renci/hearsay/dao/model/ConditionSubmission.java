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
@XmlRootElement(name = "conditionSubmission")
@XmlType(name = "ConditionSubmission")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "condition_submission")
public class ConditionSubmission extends BaseEntity {

    private static final long serialVersionUID = 488688608486958944L;

    @ManyToOne
    @JoinColumn(name = "variant_assertion_fid")
    private VariantAssertion variantAssertion;

    @ManyToOne
    @JoinColumn(name = "resolved_condition_fid")
    private ResolvedCondition resolvedCondition;

    public ConditionSubmission() {
        super();
    }

}
