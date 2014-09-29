package org.renci.hearsay.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "geneConditionAssertion")
@XmlType(name = "GeneConditionAssertion")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "gene_condition_assertion")
public class GeneConditionAssertion extends BaseEntity {

    private static final long serialVersionUID = 2500491400614225551L;

    @Column(name = "clinical_significance")
    private String clinicalSignificance;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "inheritance_pattern")
    private String inheritancePattern;

    @Column(name = "penetrance")
    private Float penatrance;

    @Column(name = "mechanism")
    private String mechanism;

    @Column(name = "assertion_method")
    private String assertionMethod;

    @Column(name = "strength_of_evidence")
    private String strengthOfEvidence;

    @Column(name = "date_last_reviewed")
    private Date lastReviewed;

    public GeneConditionAssertion() {
        super();
    }

    public String getClinicalSignificance() {
        return clinicalSignificance;
    }

    public void setClinicalSignificance(String clinicalSignificance) {
        this.clinicalSignificance = clinicalSignificance;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getInheritancePattern() {
        return inheritancePattern;
    }

    public void setInheritancePattern(String inheritancePattern) {
        this.inheritancePattern = inheritancePattern;
    }

    public Float getPenatrance() {
        return penatrance;
    }

    public void setPenatrance(Float penatrance) {
        this.penatrance = penatrance;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getAssertionMethod() {
        return assertionMethod;
    }

    public void setAssertionMethod(String assertionMethod) {
        this.assertionMethod = assertionMethod;
    }

    public String getStrengthOfEvidence() {
        return strengthOfEvidence;
    }

    public void setStrengthOfEvidence(String strengthOfEvidence) {
        this.strengthOfEvidence = strengthOfEvidence;
    }

    public Date getLastReviewed() {
        return lastReviewed;
    }

    public void setLastReviewed(Date lastReviewed) {
        this.lastReviewed = lastReviewed;
    }

    @Override
    public String toString() {
        return String
                .format("GeneConditionAssertion [id=%s, clinicalSignificance=%s, identifier=%s, inheritancePattern=%s, penatrance=%s, mechanism=%s, assertionMethod=%s, strengthOfEvidence=%s, lastReviewed=%s]",
                        id, clinicalSignificance, identifier, inheritancePattern, penatrance, mechanism,
                        assertionMethod, strengthOfEvidence, lastReviewed);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((assertionMethod == null) ? 0 : assertionMethod.hashCode());
        result = prime * result + ((clinicalSignificance == null) ? 0 : clinicalSignificance.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((inheritancePattern == null) ? 0 : inheritancePattern.hashCode());
        result = prime * result + ((lastReviewed == null) ? 0 : lastReviewed.hashCode());
        result = prime * result + ((mechanism == null) ? 0 : mechanism.hashCode());
        result = prime * result + ((penatrance == null) ? 0 : penatrance.hashCode());
        result = prime * result + ((strengthOfEvidence == null) ? 0 : strengthOfEvidence.hashCode());
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
        GeneConditionAssertion other = (GeneConditionAssertion) obj;
        if (assertionMethod == null) {
            if (other.assertionMethod != null)
                return false;
        } else if (!assertionMethod.equals(other.assertionMethod))
            return false;
        if (clinicalSignificance == null) {
            if (other.clinicalSignificance != null)
                return false;
        } else if (!clinicalSignificance.equals(other.clinicalSignificance))
            return false;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (inheritancePattern == null) {
            if (other.inheritancePattern != null)
                return false;
        } else if (!inheritancePattern.equals(other.inheritancePattern))
            return false;
        if (lastReviewed == null) {
            if (other.lastReviewed != null)
                return false;
        } else if (!lastReviewed.equals(other.lastReviewed))
            return false;
        if (mechanism == null) {
            if (other.mechanism != null)
                return false;
        } else if (!mechanism.equals(other.mechanism))
            return false;
        if (penatrance == null) {
            if (other.penatrance != null)
                return false;
        } else if (!penatrance.equals(other.penatrance))
            return false;
        if (strengthOfEvidence == null) {
            if (other.strengthOfEvidence != null)
                return false;
        } else if (!strengthOfEvidence.equals(other.strengthOfEvidence))
            return false;
        return true;
    }

}
