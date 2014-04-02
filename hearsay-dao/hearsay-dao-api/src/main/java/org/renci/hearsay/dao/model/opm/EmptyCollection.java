package org.renci.hearsay.dao.model.opm;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for EmptyCollection complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmptyCollection">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/ns/prov#}Collection">
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmptyCollection")
@javax.persistence.Entity
@Table(name = "opm_empty_collection")
public class EmptyCollection extends Collection {

    private static final long serialVersionUID = 6902706357354600339L;

}
