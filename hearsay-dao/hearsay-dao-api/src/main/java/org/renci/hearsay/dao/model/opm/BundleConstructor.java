package org.renci.hearsay.dao.model.opm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * <p>
 * Java class for BundleConstructor complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleConstructor">
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
 *       &lt;/choice>
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
@XmlType(name = "BundleConstructor", propOrder = { "entityOrActivityOrWasGeneratedBy" })
@javax.persistence.Entity
@Table(name = "opm_bundle_constructor")
public class BundleConstructor {

    @XmlElementRefs({
            @XmlElementRef(name = "wasStartedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "agent", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "emptyCollection", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasInfluencedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "internalElement", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasAssociatedWith", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasQuotedFrom", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "used", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasDerivedFrom", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "organization", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "softwareAgent", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "wasInformedBy", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "bundle", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "entity", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false),
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
            @XmlElementRef(name = "activity", namespace = "http://www.w3.org/ns/prov#", type = JAXBElement.class, required = false) })
    protected Set<Element> entityOrActivityOrWasGeneratedBy;

    @XmlAttribute(name = "id", namespace = "http://www.w3.org/ns/prov#")
    @XmlJavaTypeAdapter(QNameAdapter.class)
    @ManyToOne
    @JoinColumn(name = "id")
    protected QualifiedName id;

    @XmlAnyAttribute
    @Transient
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

}
