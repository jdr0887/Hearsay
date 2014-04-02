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
 * Java class for KeyEntityPair complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeyEntityPair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="entity" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyEntityPair", propOrder = {})
@javax.persistence.Entity
@Table(name = "opm_key_entity_pair")
public class KeyEntityPair extends Element {

    private static final long serialVersionUID = -6494270978560496039L;

    @XmlElement(required = true)
    @ManyToOne
    @JoinColumn(name = "key")
    protected Key key;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "entity")
    protected QualifiedName entity;

    @ManyToOne
    @JoinColumn(name = "dictionary_membership")
    private DictionaryMembership dictionaryMembership;

    public KeyEntityPair() {
        super();
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public QualifiedName getEntity() {
        return entity;
    }

    public void setEntity(QualifiedName entity) {
        this.entity = entity;
    }

    public DictionaryMembership getDictionaryMembership() {
        return dictionaryMembership;
    }

    public void setDictionaryMembership(DictionaryMembership dictionaryMembership) {
        this.dictionaryMembership = dictionaryMembership;
    }

    @Override
    public String toString() {
        return String.format("KeyEntityPair [primaryKey=%s, key=%s, entity=%s, dictionaryMembership=%s]", primaryKey,
                key, entity, dictionaryMembership);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((dictionaryMembership == null) ? 0 : dictionaryMembership.hashCode());
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
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
        KeyEntityPair other = (KeyEntityPair) obj;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (dictionaryMembership == null) {
            if (other.dictionaryMembership != null)
                return false;
        } else if (!dictionaryMembership.equals(other.dictionaryMembership))
            return false;
        if (entity == null) {
            if (other.entity != null)
                return false;
        } else if (!entity.equals(other.entity))
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        return true;
    }

}
