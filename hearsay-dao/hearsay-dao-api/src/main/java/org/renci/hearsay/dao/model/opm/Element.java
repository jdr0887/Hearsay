package org.renci.hearsay.dao.model.opm;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.renci.hearsay.dao.model.Persistable;

@XmlTransient
@javax.persistence.Entity
@Table(name = "opm_element")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
public class Element implements Persistable {

    private static final long serialVersionUID = -4073470843291923798L;

    @XmlAttribute(name = "pk")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opm_element_sequence")
    @SequenceGenerator(name = "opm_element_sequence", sequenceName = "opm_element_sequence", allocationSize = 1, initialValue = 1)
    @Column(name = "element_pk", nullable = false)
    protected Long primaryKey;

    public Element() {
        super();
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

}
