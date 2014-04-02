package org.renci.hearsay.dao.model.opm;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for EmptyDictionary complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmptyDictionary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/ns/prov#}Dictionary">
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmptyDictionary")
@javax.persistence.Entity
@Table(name = "opm_empty_dictionary")
public class EmptyDictionary extends Dictionary {

    private static final long serialVersionUID = -3406828946420143816L;

}
