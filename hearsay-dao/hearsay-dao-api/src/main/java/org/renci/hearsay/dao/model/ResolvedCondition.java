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
@XmlRootElement(name = "resolvedCondition")
@XmlType(name = "ResolvedCondition")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "resolved_condition")
public class ResolvedCondition extends BaseEntity {

    private static final long serialVersionUID = 3545067654718481321L;

    @OneToMany(mappedBy = "resolvedCondition", fetch = FetchType.EAGER)
    private Set<GeneCondition> geneConditions;

    @OneToMany(mappedBy = "variantAssertion", fetch = FetchType.EAGER)
    private Set<ConditionSubmission> conditionSubmissions;

    @OneToMany(mappedBy = "resolvedCondition", fetch = FetchType.EAGER)
    private Set<ResolvedConditionIdentifier> resolvedConditionIdentifiers;

    public ResolvedCondition() {
        super();
    }

}
