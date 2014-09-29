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
@XmlRootElement(name = "variantInPersonGroup")
@XmlType(name = "VariantInPersonGroup")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "variant_in_person_group")
public class VariantInPersonGroup extends BaseEntity {

    private static final long serialVersionUID = -4352362845693082406L;

    public VariantInPersonGroup() {
        super();
    }

}
