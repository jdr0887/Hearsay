package org.renci.hearsay.dao.model.opm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * <p>
 * Java class for Delegation complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Delegation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="delegate" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="responsible" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="activity" type="{http://www.w3.org/ns/prov#}IDRef" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}label" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/ns/prov#}id"/>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Delegation", propOrder = { "delegate", "responsible", "activity", "label", "type", "any" })
@javax.persistence.Entity
@Table(name = "opm_delegation")
public class Delegation extends Element {

    private static final long serialVersionUID = -7438489047493658499L;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "delegate")
    protected QualifiedName delegate;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "responsible")
    protected QualifiedName responsible;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "responsible")
    protected QualifiedName activity;

    @OneToMany(mappedBy = "delegation", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<InternationalizedString> label;

    @OneToMany(mappedBy = "delegation", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<Type> type;

    @XmlAnyElement(lax = true)
    @Transient
    protected List<Object> any;

    @XmlAttribute(name = "id", namespace = "http://www.w3.org/ns/prov#")
    @XmlJavaTypeAdapter(QNameAdapter.class)
    @ManyToOne
    @JoinColumn(name = "id")
    protected QualifiedName id;

    @XmlAnyAttribute
    @Transient
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    public Delegation() {
        super();
        label = new HashSet<InternationalizedString>();
        type = new HashSet<Type>();
        otherAttributes = new HashMap<QName, String>();
    }

    public QualifiedName getDelegate() {
        return delegate;
    }

    public void setDelegate(QualifiedName delegate) {
        this.delegate = delegate;
    }

    public QualifiedName getResponsible() {
        return responsible;
    }

    public void setResponsible(QualifiedName responsible) {
        this.responsible = responsible;
    }

    public QualifiedName getActivity() {
        return activity;
    }

    public void setActivity(QualifiedName activity) {
        this.activity = activity;
    }

    public Set<InternationalizedString> getLabel() {
        return label;
    }

    public void setLabel(Set<InternationalizedString> label) {
        this.label = label;
    }

    public Set<Type> getType() {
        return type;
    }

    public void setType(Set<Type> type) {
        this.type = type;
    }

    public List<Object> getAny() {
        return any;
    }

    public void setAny(List<Object> any) {
        this.any = any;
    }

    public QualifiedName getId() {
        return id;
    }

    public void setId(QualifiedName id) {
        this.id = id;
    }

    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    @Override
    public String toString() {
        return String.format("Delegation [primaryKey=%s, id=%s, delegate=%s, responsible=%s, activity=%s]", primaryKey,
                id, delegate, responsible, activity);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((delegate == null) ? 0 : delegate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((responsible == null) ? 0 : responsible.hashCode());
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
        Delegation other = (Delegation) obj;
        if (activity == null) {
            if (other.activity != null)
                return false;
        } else if (!activity.equals(other.activity))
            return false;
        if (delegate == null) {
            if (other.delegate != null)
                return false;
        } else if (!delegate.equals(other.delegate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (responsible == null) {
            if (other.responsible != null)
                return false;
        } else if (!responsible.equals(other.responsible))
            return false;
        return true;
    }

}
