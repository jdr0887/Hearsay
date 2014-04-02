package org.renci.hearsay.dao.model.opm;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Document complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.w3.org/ns/prov#}entity"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}activity"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasGeneratedBy"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}used"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasInformedBy"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasStartedBy"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasEndedBy"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasInvalidatedBy"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasDerivedFrom"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasRevisionOf"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasQuotedFrom"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}hadPrimarySource"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}agent"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}person"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}organization"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}softwareAgent"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasAttributedTo"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasAssociatedWith"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}actedOnBehalfOf"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}wasInfluencedBy"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}bundle"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}specializationOf"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}alternateOf"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}collection"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}emptyCollection"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}hadMember"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}plan"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}other"/>
 *         &lt;element ref="{http://www.w3.org/ns/prov#}internalElement"/>
 *         &lt;element name="bundleContent" type="{http://www.w3.org/ns/prov#}BundleConstructor"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = { "entityOrActivityOrWasGeneratedBy" })
@javax.persistence.Entity
@Table(name = "opm_document")
public class Document extends Element {

    private static final long serialVersionUID = -5512618575408921575L;

    @XmlElementRefs({
            @XmlElementRef(name = "wasStartedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "agent", namespace = "http://www.w3.org/ns/prov#", type = Agent.class, required = false),
            @XmlElementRef(name = "emptyCollection", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasInfluencedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "internalElement", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasAssociatedWith", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasQuotedFrom", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "bundleContent", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "used", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasDerivedFrom", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "organization", namespace = "http://www.w3.org/ns/prov#", type = Organization.class, required = false),
            @XmlElementRef(name = "softwareAgent", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasInformedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "bundle", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "entity", namespace = "http://www.w3.org/ns/prov#", type = Entity.class, required = false),
            @XmlElementRef(name = "alternateOf", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasAttributedTo", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasGeneratedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasInvalidatedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "hadMember", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "specializationOf", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "other", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasEndedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "plan", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "actedOnBehalfOf", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "hadPrimarySource", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "person", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "collection", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasRevisionOf", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "activity", namespace = "http://www.w3.org/ns/prov#", type = Activity.class, required = false) })
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    protected Set<Element> entityOrActivityOrWasGeneratedBy;

    public Document() {
        super();
        this.entityOrActivityOrWasGeneratedBy = new HashSet<Element>();
    }

    public Set<Element> getEntityOrActivityOrWasGeneratedBy() {
        return entityOrActivityOrWasGeneratedBy;
    }

    public void setEntityOrActivityOrWasGeneratedBy(Set<Element> entityOrActivityOrWasGeneratedBy) {
        this.entityOrActivityOrWasGeneratedBy = entityOrActivityOrWasGeneratedBy;
    }

    @Override
    public String toString() {
        return String.format("Document [primaryKey=%s]", primaryKey);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Document other = (Document) obj;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        return true;
    }

}
