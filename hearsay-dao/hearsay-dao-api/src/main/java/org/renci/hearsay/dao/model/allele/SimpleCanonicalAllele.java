package org.renci.hearsay.dao.model.allele;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "simpleCanonicalAllele")
@XmlType(name = "SimpleCanonicalAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "simple_canonical_allele")
public class SimpleCanonicalAllele extends CanonicalAllele {

    private static final long serialVersionUID = -7787729412698110779L;

    public SimpleCanonicalAllele() {
        super();
    }

    @Override
    public String toString() {
        return String.format("SimpleCanonicalAllele [id=%s, active=%s]", id, active);
    }

}
