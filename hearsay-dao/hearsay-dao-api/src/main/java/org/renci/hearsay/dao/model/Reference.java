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
@XmlRootElement(name = "reference")
@XmlType(name = "Reference")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "reference")
public class Reference extends BaseEntity {

    private static final long serialVersionUID = 7708206583385166591L;

}
