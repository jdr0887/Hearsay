package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "submitter")
@XmlType(name = "Submitter")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "submitter")
public class Submitter extends BaseEntity {

    private static final long serialVersionUID = 130521597660269328L;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "evidence_summary_statement")
    private String evidenceSummaryStatement;

    @Column(name = "organization")
    private String organization;

    // @OneToMany(mappedBy = "submitter", fetch = FetchType.EAGER)
    // private Set<VariantAssertion> assertions;

    public Submitter() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvidenceSummaryStatement() {
        return evidenceSummaryStatement;
    }

    public void setEvidenceSummaryStatement(String evidenceSummaryStatement) {
        this.evidenceSummaryStatement = evidenceSummaryStatement;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

}
