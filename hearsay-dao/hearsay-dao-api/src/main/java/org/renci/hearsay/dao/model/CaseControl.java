package org.renci.hearsay.dao.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "caseControl")
@XmlType(name = "CaseControl")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "case_control")
public class CaseControl extends BaseEntity {

    private static final long serialVersionUID = -1731628341139926007L;

    private Boolean germline;

    private Boolean tumor;

    private Boolean priorSequencing;

}
