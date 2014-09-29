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
@XmlRootElement(name = "studySubject")
@XmlType(name = "StudySubject")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "study_subject")
public class StudySubject extends BaseEntity {

    private static final long serialVersionUID = 5737477199085873331L;

    private String type;

    private String name;

    private Integer size;

}
