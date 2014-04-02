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
 * Java class for Influence complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Influence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="influencee" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="influencer" type="{http://www.w3.org/ns/prov#}IDRef"/>
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
@XmlType(name = "Influence", propOrder = { "influencee", "influencer", "label", "type", "any" })
@javax.persistence.Entity
@Table(name = "opm_influence")
public class Influence extends Element {

    private static final long serialVersionUID = -613700214278933819L;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "influencee")
    protected QualifiedName influencee;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "influencer")
    protected QualifiedName influencer;

    @OneToMany(mappedBy = "influence", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<InternationalizedString> label;

    @OneToMany(mappedBy = "influence", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
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

    public Influence() {
        super();
        label = new HashSet<InternationalizedString>();
        type = new HashSet<Type>();
        otherAttributes = new HashMap<QName, String>();
    }

    public QualifiedName getInfluencee() {
        return influencee;
    }

    public void setInfluencee(QualifiedName influencee) {
        this.influencee = influencee;
    }

    public QualifiedName getInfluencer() {
        return influencer;
    }

    public void setInfluencer(QualifiedName influencer) {
        this.influencer = influencer;
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
        return String.format("Influence [primaryKey=%s, id=%s, influencee=%s, influencer=%s]", primaryKey, id,
                influencee, influencer);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((influencee == null) ? 0 : influencee.hashCode());
        result = prime * result + ((influencer == null) ? 0 : influencer.hashCode());
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
        Influence other = (Influence) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (influencee == null) {
            if (other.influencee != null)
                return false;
        } else if (!influencee.equals(other.influencee))
            return false;
        if (influencer == null) {
            if (other.influencer != null)
                return false;
        } else if (!influencer.equals(other.influencer))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        return true;
    }

}
