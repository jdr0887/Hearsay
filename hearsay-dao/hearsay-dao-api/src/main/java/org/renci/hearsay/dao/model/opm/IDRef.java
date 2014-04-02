package org.renci.hearsay.dao.model.opm;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

/**
 * <p>
 * Java class for IDRef complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDRef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute ref="{http://www.w3.org/ns/prov#}ref use="required""/>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDRef")
public class IDRef {

    @XmlAttribute(name = "ref", namespace = "http://www.w3.org/ns/prov#", required = true)
    protected QName ref;

    @XmlAnyAttribute
    @Transient
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    public QName getRef() {
        return ref;
    }

    public void setRef(QName ref) {
        this.ref = ref;
    }

}
