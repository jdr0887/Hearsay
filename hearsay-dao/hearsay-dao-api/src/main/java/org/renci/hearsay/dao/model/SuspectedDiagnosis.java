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
@XmlRootElement(name = "suspectedDiagnosis")
@XmlType(name = "SuspectedDiagnosis")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "suspected_diagnosis")
public class SuspectedDiagnosis extends BaseEntity {

    private static final long serialVersionUID = -247501783394999943L;

    @ManyToOne
    @JoinColumn(name = "individual_fid")
    private Individual individual;

    public SuspectedDiagnosis() {
        super();
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

}
