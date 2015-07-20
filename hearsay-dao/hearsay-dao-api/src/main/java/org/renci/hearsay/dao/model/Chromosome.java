package org.renci.hearsay.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.openjpa.persistence.jdbc.Index;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@XmlRootElement(name = "chromosome")
@XmlType(propOrder = { "name" })
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "chromosome", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@NamedQueries({ @NamedQuery(name = "Chromosome.findByName", query = "SELECT a FROM Chromosome a where a.name = :name") })
public class Chromosome extends DictionaryEntity {

    private static final long serialVersionUID = 2931123376533316569L;

    @XmlAttribute
    @Index
    @Column(name = "name")
    private String name;

    public Chromosome() {
        super();
    }

    public Chromosome(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Chromosome [id=%s, name=%s]", id, name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Chromosome other = (Chromosome) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
