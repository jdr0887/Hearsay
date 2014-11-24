package org.renci.hearsay.dao.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @Column(name = "clinical_significance")
    private String clinicalSignificance;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "method")
    private String method;

    @Column(name = "last_reviewed")
    private Date lastReviewed;

    @Column(name = "review_level_status")
    private String reviewLevelStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "submitter_fid")
    private Submitter submitter;

    @OneToMany(mappedBy = "variantAssertion", fetch = FetchType.EAGER)
    private Set<ConditionSubmission> conditionSubmissions;

    @OneToMany(mappedBy = "variantAssertion", fetch = FetchType.EAGER)
    private Set<VariantSubmission> variantSubmissions;

    public VariantAssertion() {
        super();
    }

}
