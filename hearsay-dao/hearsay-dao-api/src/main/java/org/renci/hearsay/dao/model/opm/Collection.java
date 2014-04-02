package org.renci.hearsay.dao.model.opm;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Collection complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Collection">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/ns/prov#}Entity">
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Collection")
@XmlSeeAlso({ EmptyCollection.class, Dictionary.class })
@javax.persistence.Entity
@Table(name = "opm_collection")
public class Collection extends Entity {

    private static final long serialVersionUID = -6000708894311978410L;

}
