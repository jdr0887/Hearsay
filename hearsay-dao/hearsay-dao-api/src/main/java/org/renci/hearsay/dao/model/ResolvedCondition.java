package org.renci.hearsay.dao.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.renci.hearsay.dao.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "resolvedCondition")
@XmlType(name = "ResolvedCondition")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "resolved_condition")
public class ResolvedCondition implements Persistable {

    private static final long serialVersionUID = 3545067654718481321L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resolved_condition_id_seq")
    @SequenceGenerator(name = "resolved_condition_id_seq", sequenceName = "resolved_condition_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "resolvedCondition", fetch = FetchType.EAGER)
    private Set<GeneCondition> geneConditions;

    @OneToMany(mappedBy = "variantAssertion", fetch = FetchType.EAGER)
    private Set<ConditionSubmission> conditionSubmissions;

    @OneToMany(mappedBy = "resolvedCondition", fetch = FetchType.EAGER)
    private Set<ResolvedConditionIdentifier> resolvedConditionIdentifiers;

    public ResolvedCondition() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String toString() {
        return String.format("ResolvedCondition [id=%s]", id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        ResolvedCondition other = (ResolvedCondition) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
