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
@XmlRootElement(name = "complexCanonicalAllele")
@XmlType(name = "ComplexCanonicalAllele")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "complex_canonical_allele")
public class ComplexCanonicalAllele extends CanonicalAllele {

    private static final long serialVersionUID = 8336483738342746046L;

    public ComplexCanonicalAllele() {
        super();
    }

    @Override
    public String toString() {
        return String.format("ComplexCanonicalAllele [id=%s, active=%s]", id, active);
    }

}
