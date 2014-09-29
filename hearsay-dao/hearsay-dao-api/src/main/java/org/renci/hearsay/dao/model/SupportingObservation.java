package org.renci.hearsay.dao.model;

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
@XmlRootElement(name = "supportingObservation")
@XmlType(name = "SupportingObservation")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "supporting_observation")
public class SupportingObservation extends BaseEntity {

    private static final long serialVersionUID = -8220489659799273363L;

    @Column(name = "geophraphic_origin")
    private Integer geophraphicOrigin;

    @Column(name = "ethnicity")
    private Integer ethnicity;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "allele_origin")
    private Integer alleleOrigin;

    public SupportingObservation() {
        super();
    }

}
