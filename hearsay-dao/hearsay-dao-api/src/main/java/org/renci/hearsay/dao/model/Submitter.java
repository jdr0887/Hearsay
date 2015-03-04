package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@XmlRootElement(name = "submitter")
@XmlType(name = "Submitter")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "submitter")
public class Submitter implements Persistable {

    private static final long serialVersionUID = 130521597660269328L;

    @XmlAttribute(name = "id")
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submitter_id_seq")
    @SequenceGenerator(name = "submitter_id_seq", sequenceName = "submitter_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return String.format("Submitter [id=%s, name=%s, type=%s, evidenceSummaryStatement=%s, organization=%s]", id,
                name, type, evidenceSummaryStatement, organization);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((evidenceSummaryStatement == null) ? 0 : evidenceSummaryStatement.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((organization == null) ? 0 : organization.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Submitter other = (Submitter) obj;
        if (evidenceSummaryStatement == null) {
            if (other.evidenceSummaryStatement != null)
                return false;
        } else if (!evidenceSummaryStatement.equals(other.evidenceSummaryStatement))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (organization == null) {
            if (other.organization != null)
                return false;
        } else if (!organization.equals(other.organization))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
