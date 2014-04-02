package org.renci.hearsay.dao.model.opm;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Dictionary complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dictionary">
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
@XmlType(name = "Dictionary")
@XmlSeeAlso({ EmptyDictionary.class })
@javax.persistence.Entity
@Table(name = "opm_dictionary")
public class Dictionary extends Collection {

    private static final long serialVersionUID = 7319059471315066294L;

}
