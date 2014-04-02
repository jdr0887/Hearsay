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
 * Java class for DictionaryMembership complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DictionaryMembership">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dictionary" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="keyEntityPair" type="{http://www.w3.org/ns/prov#}KeyEntityPair" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DictionaryMembership", propOrder = { "dictionary", "keyEntityPair" })
@javax.persistence.Entity
@Table(name = "opm_dictionary_membership")
public class DictionaryMembership extends Element {

    private static final long serialVersionUID = -622521269363537700L;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "dictionary")
    protected QualifiedName dictionary;

    @XmlElement(required = true)
    @OneToMany(mappedBy = "dictionaryMembership", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    protected Set<KeyEntityPair> keyEntityPair;

    public DictionaryMembership() {
        super();
    }

    public QualifiedName getDictionary() {
        return dictionary;
    }

    public void setDictionary(QualifiedName dictionary) {
        this.dictionary = dictionary;
    }

    public Set<KeyEntityPair> getKeyEntityPair() {
        return keyEntityPair;
    }

    public void setKeyEntityPair(Set<KeyEntityPair> keyEntityPair) {
        this.keyEntityPair = keyEntityPair;
    }

    @Override
    public String toString() {
        return String.format("DictionaryMembership [primaryKey=%s, dictionary=%s, keyEntityPair=%s]", primaryKey,
                dictionary, keyEntityPair);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((dictionary == null) ? 0 : dictionary.hashCode());
        result = prime * result + ((keyEntityPair == null) ? 0 : keyEntityPair.hashCode());
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
        DictionaryMembership other = (DictionaryMembership) obj;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (dictionary == null) {
            if (other.dictionary != null)
                return false;
        } else if (!dictionary.equals(other.dictionary))
            return false;
        if (keyEntityPair == null) {
            if (other.keyEntityPair != null)
                return false;
        } else if (!keyEntityPair.equals(other.keyEntityPair))
            return false;
        return true;
    }

}
