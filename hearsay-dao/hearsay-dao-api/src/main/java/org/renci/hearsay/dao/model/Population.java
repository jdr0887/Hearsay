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
@XmlRootElement(name = "population")
@XmlType(name = "Population")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "population")
public class Population extends PeopleGroup {

    private static final long serialVersionUID = 6596798292115433440L;

    public Population() {
        super();
    }

}
