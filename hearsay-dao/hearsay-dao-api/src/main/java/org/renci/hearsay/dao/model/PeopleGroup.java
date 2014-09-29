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
@XmlRootElement(name = "peopleGroup")
@XmlType(name = "PeopleGroup")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "people_group")
public class PeopleGroup extends BaseEntity {

    private static final long serialVersionUID = -1439340003338849664L;

    public PeopleGroup() {
        super();
    }

}
