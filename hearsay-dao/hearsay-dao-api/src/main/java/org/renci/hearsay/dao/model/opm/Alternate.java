package org.renci.hearsay.dao.model.opm;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for Alternate complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Alternate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alternate1" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="alternate2" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Alternate", propOrder = { "alternate1", "alternate2" })
@javax.persistence.Entity
@Table(name = "opm_alternate")
public class Alternate extends Element {

    private static final long serialVersionUID = -225291731261729920L;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "alternate_1")
    protected QualifiedName alternate1;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "alternate_2")
    protected QualifiedName alternate2;

    public Alternate() {
        super();
    }

    public QualifiedName getAlternate1() {
        return alternate1;
    }

    public void setAlternate1(QualifiedName alternate1) {
        this.alternate1 = alternate1;
    }

    public QualifiedName getAlternate2() {
        return alternate2;
    }

    public void setAlternate2(QualifiedName alternate2) {
        this.alternate2 = alternate2;
    }

    @Override
    public String toString() {
        return String.format("Alternate [primaryKey=%s, alternate1=%s, alternate2=%s]", primaryKey, alternate1,
                alternate2);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alternate1 == null) ? 0 : alternate1.hashCode());
        result = prime * result + ((alternate2 == null) ? 0 : alternate2.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alternate other = (Alternate) obj;
        if (alternate1 == null) {
            if (other.alternate1 != null)
                return false;
        } else if (!alternate1.equals(other.alternate1))
            return false;
        if (alternate2 == null) {
            if (other.alternate2 != null)
                return false;
        } else if (!alternate2.equals(other.alternate2))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        return true;
    }

}
