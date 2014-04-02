package org.renci.hearsay.dao.model.opm;

import java.util.Date;
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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * <p>
 * Java class for Start complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Start">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activity" type="{http://www.w3.org/ns/prov#}IDRef"/>
 *         &lt;element name="trigger" type="{http://www.w3.org/ns/prov#}IDRef" minOccurs="0"/>
 *         &lt;element name="starter" type="{http://www.w3.org/ns/prov#}IDRef" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}label" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}location" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}role" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "Start", propOrder = { "activity", "trigger", "starter", "time", "label", "location", "role", "type",
        "any" })
@javax.persistence.Entity
@Table(name = "opm_start")
public class Start extends Element {

    private static final long serialVersionUID = 1479761005570331368L;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(required = true, type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "activity")
    protected QualifiedName activity;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "trigger")
    protected QualifiedName trigger;

    @XmlJavaTypeAdapter(IDRefAdapter.class)
    @XmlElement(type = IDRef.class)
    @ManyToOne
    @JoinColumn(name = "starter")
    protected QualifiedName starter;

    @XmlSchemaType(name = "dateTime")
    protected Date time;

    @OneToMany(mappedBy = "start", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<InternationalizedString> label;

    @OneToMany(mappedBy = "start", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<Location> location;

    @OneToMany(mappedBy = "usage", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<Role> role;

    @OneToMany(mappedBy = "start", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
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

    public Start() {
        super();
        label = new HashSet<InternationalizedString>();
        location = new HashSet<Location>();
        type = new HashSet<Type>();
        otherAttributes = new HashMap<QName, String>();
    }

    public QualifiedName getActivity() {
        return activity;
    }

    public void setActivity(QualifiedName activity) {
        this.activity = activity;
    }

    public QualifiedName getTrigger() {
        return trigger;
    }

    public void setTrigger(QualifiedName trigger) {
        this.trigger = trigger;
    }

    public QualifiedName getStarter() {
        return starter;
    }

    public void setStarter(QualifiedName starter) {
        this.starter = starter;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Set<InternationalizedString> getLabel() {
        return label;
    }

    public void setLabel(Set<InternationalizedString> label) {
        this.label = label;
    }

    public Set<Location> getLocation() {
        return location;
    }

    public void setLocation(Set<Location> location) {
        this.location = location;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
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
        return String.format("Start [primaryKey=%s, id=%s, activity=%s, trigger=%s, starter=%s, time=%s]", primaryKey,
                id, activity, trigger, starter, time);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((activity == null) ? 0 : activity.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + ((starter == null) ? 0 : starter.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((trigger == null) ? 0 : trigger.hashCode());
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
        Start other = (Start) obj;
        if (activity == null) {
            if (other.activity != null)
                return false;
        } else if (!activity.equals(other.activity))
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
        if (starter == null) {
            if (other.starter != null)
                return false;
        } else if (!starter.equals(other.starter))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (trigger == null) {
            if (other.trigger != null)
                return false;
        } else if (!trigger.equals(other.trigger))
            return false;
        return true;
    }

}
