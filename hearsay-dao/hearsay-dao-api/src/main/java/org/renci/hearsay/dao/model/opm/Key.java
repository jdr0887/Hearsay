package org.renci.hearsay.dao.model.opm;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Key", propOrder = {})
@javax.persistence.Entity
@Table(name = "opm_key")
public class Key extends Element {

    private static final long serialVersionUID = 7984793255531141012L;

    @XmlSchemaType(name = "anySimpleType")
    @Column(name = "value")
    protected Object value;

    @XmlJavaTypeAdapter(QNameAdapter.class)
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/2001/XMLSchema-instance")
    @ManyToOne
    @JoinColumn(name = "type")
    protected QualifiedName type;

    @ManyToOne
    @JoinColumn(name = "removal_fk")
    private Removal removal;

    public Key() {
        super();
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public QualifiedName getType() {
        return type;
    }

    public void setType(QualifiedName type) {
        this.type = type;
    }

    public Removal getRemoval() {
        return removal;
    }

    public void setRemoval(Removal removal) {
        this.removal = removal;
    }

    @Override
    public String toString() {
        return String.format("Key [primaryKey=%s, value=%s, type=%s, removal=%s]", primaryKey, value, type, removal);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((removal == null) ? 0 : removal.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Key other = (Key) obj;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (removal == null) {
            if (other.removal != null)
                return false;
        } else if (!removal.equals(other.removal))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}
