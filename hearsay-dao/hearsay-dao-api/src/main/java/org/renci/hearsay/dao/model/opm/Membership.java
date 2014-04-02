package org.renci.hearsay.dao.model.opm;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for Membership complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Membership">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="collection" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="entity" type="{http://www.w3.org/ns/prov#}IDRef" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Membership", propOrder = { "collection", "entity" })
@javax.persistence.Entity
@Table(name = "opm_membership")
public class Membership extends Element {

    private static final long serialVersionUID = 1074145855908549672L;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "collection")
    protected QualifiedName collection;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @OneToMany(mappedBy = "membership", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<QualifiedName> entity;

    public Membership() {
        super();
    }

    public QualifiedName getCollection() {
        return collection;
    }

    public void setCollection(QualifiedName collection) {
        this.collection = collection;
    }

    public Set<QualifiedName> getEntity() {
        return entity;
    }

    public void setEntity(Set<QualifiedName> entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return String.format("Membership [primaryKey=%s, collection=%s]", primaryKey, collection);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((collection == null) ? 0 : collection.hashCode());
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
        Membership other = (Membership) obj;
        if (collection == null) {
            if (other.collection != null)
                return false;
        } else if (!collection.equals(other.collection))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        return true;
    }

}
