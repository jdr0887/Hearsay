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

    public Set<GeneCondition> getGeneConditions() {
        return geneConditions;
    }

    public void setGeneConditions(Set<GeneCondition> geneConditions) {
        this.geneConditions = geneConditions;
    }

    public Set<ConditionSubmission> getConditionSubmissions() {
        return conditionSubmissions;
    }

    public void setConditionSubmissions(Set<ConditionSubmission> conditionSubmissions) {
        this.conditionSubmissions = conditionSubmissions;
    }

    public Set<ResolvedConditionIdentifier> getResolvedConditionIdentifiers() {
        return resolvedConditionIdentifiers;
    }

    public void setResolvedConditionIdentifiers(Set<ResolvedConditionIdentifier> resolvedConditionIdentifiers) {
        this.resolvedConditionIdentifiers = resolvedConditionIdentifiers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((conditionSubmissions == null) ? 0 : conditionSubmissions.hashCode());
        result = prime * result + ((geneConditions == null) ? 0 : geneConditions.hashCode());
        result = prime * result
                + ((resolvedConditionIdentifiers == null) ? 0 : resolvedConditionIdentifiers.hashCode());
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
        ResolvedCondition other = (ResolvedCondition) obj;
        if (conditionSubmissions == null) {
            if (other.conditionSubmissions != null)
                return false;
        } else if (!conditionSubmissions.equals(other.conditionSubmissions))
            return false;
        if (geneConditions == null) {
            if (other.geneConditions != null)
                return false;
        } else if (!geneConditions.equals(other.geneConditions))
            return false;
        if (resolvedConditionIdentifiers == null) {
            if (other.resolvedConditionIdentifiers != null)
                return false;
        } else if (!resolvedConditionIdentifiers.equals(other.resolvedConditionIdentifiers))
            return false;
        return true;
    }

}
